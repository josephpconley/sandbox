package bintree
/**
 * Copyright (C) 2009-2013 Typesafe Inc. <http://www.typesafe.com>
 */

import akka.actor._
import scala.collection.immutable.Queue

object BinaryTreeSet {

  trait Operation {
    def requester: ActorRef
    def id: Int
    def elem: Int
  }

  trait OperationReply {
    def id: Int
  }

  /** Request with identifier `id` to insert an element `elem` into the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Insert(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to check whether an element `elem` is present
    * in the tree. The actor at reference `requester` should be notified when
    * this operation is completed.
    */
  case class Contains(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to remove the element `elem` from the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Remove(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request to perform garbage collection*/
  case object GC

  /** Holds the answer to the Contains request with identifier `id`.
    * `result` is true if and only if the element is present in the tree.
    */
  case class ContainsResult(id: Int, result: Boolean) extends OperationReply

  /** Message to signal successful completion of an insert or remove operation. */
  case class OperationFinished(id: Int) extends OperationReply

}


class BinaryTreeSet extends Actor {
  import BinaryTreeSet._
  import BinaryTreeNode._

  def createRoot: ActorRef = context.actorOf(BinaryTreeNode.props(0, initiallyRemoved = true))

  var root = createRoot

  // optional
  var pendingQueue = Queue.empty[Operation]

  // optional
  def receive = normal

  // optional
  /** Accepts `Operation` and `GC` messages. */
  val normal: Receive = {
    case op:BinaryTreeSet.Operation => root ! op
    case BinaryTreeSet.GC => {
      val newRoot = createRoot
      context.become(garbageCollecting(newRoot))
      root ! CopyTo(newRoot)
    }
  }

  // optional
  /** Handles messages while garbage collection is performed.
    * `newRoot` is the root of the new binary tree where we want to copy
    * all non-removed elements into.
    */
  def garbageCollecting(newRoot: ActorRef): Receive = {
    case op:BinaryTreeSet.Operation => pendingQueue = pendingQueue.enqueue(op)
    case CopyFinished => {
      //destroy all old actors and root
      context.stop(root)

      root = newRoot
      pendingQueue.foreach(op => newRoot ! op)
      pendingQueue = Queue.empty[Operation]
      context.become(normal)
    }
  }
}

object BinaryTreeNode {
  trait Position

  case object Left extends Position
  case object Right extends Position

  case class CopyTo(treeNode: ActorRef)
  case object CopyFinished

  def props(elem: Int, initiallyRemoved: Boolean) = Props(classOf[BinaryTreeNode],  elem, initiallyRemoved)
}

class BinaryTreeNode(val elem: Int, initiallyRemoved: Boolean) extends Actor {
  import BinaryTreeNode._
  import BinaryTreeSet._

  var subtrees = Map[Position, ActorRef]()
  var removed = initiallyRemoved

  // optional
  def receive = normal

  // optional
  /** Handles `Operation` messages and `CopyTo` requests. */
  val normal: Receive = {
    case Insert(req, id, e) => {
      if(e == elem && removed){
        removed = false
        req ! OperationFinished(id)
      }else if(e < elem){
        if(subtrees.contains(Left)){
          subtrees.get(Left).get ! Insert(req, id ,e)
        }else{
          subtrees = subtrees + ((Left, context.actorOf(BinaryTreeNode.props(e, false))))
          req ! OperationFinished(id)
        }
      }else if(e > elem){
        if(subtrees.contains(Right)){
          subtrees.get(Right).get ! Insert(req, id ,e)
        }else{
          subtrees = subtrees + ((Right, context.actorOf(BinaryTreeNode.props(e, false))))
          req ! OperationFinished(id)
        }
      }else{
        req ! OperationFinished(id)
      }
    }
    case Contains(req, id, e) => {
      if(e == elem){
        if(removed){
          req ! ContainsResult(id, false)
        }else{
          req ! ContainsResult(id, true)
        }
      }else if(e < elem && subtrees.contains(Left)){
        subtrees.get(Left).get ! Contains(req, id, e)
      }else if(e > elem && subtrees.contains(Right)){
        subtrees.get(Right).get ! Contains(req, id, e)
      }else{
        req ! ContainsResult(id, false)
      }
    }
    case Remove(req, id, e) => {
      if(e == elem){
        removed = true
        req ! OperationFinished(id)
      }else if(e < elem && subtrees.contains(Left)){
        subtrees.get(Left).get ! Remove(req, id ,e)
      }else if(e > elem && subtrees.contains(Right)){
        subtrees.get(Right).get ! Remove(req, id ,e)
      }else{
        req ! OperationFinished(id)
      }
    }
    case CopyTo(newNode) => {
      context.become(copying(subtrees.values.toSet, removed))
      if(removed){
        self ! OperationFinished(0)
      }else{
        newNode ! Insert(self, elem, elem)
      }

      subtrees.values.foreach(t => t ! CopyTo(newNode))
    }
  }

  // optional
  /** `expected` is the set of ActorRefs whose replies we are waiting for,
    * `insertConfirmed` tracks whether the copy of this node to the new tree has been confirmed.
    */
  def copying(expected: Set[ActorRef], insertConfirmed: Boolean): Receive = {
    case OperationFinished(id) => {
      context.become(copying(expected, true))
      if(expected.isEmpty){
        context.parent ! CopyFinished
      }
    }
    case CopyFinished => {
      val e = expected - sender
      context.become(copying(e, insertConfirmed))
      if(e.isEmpty && insertConfirmed){
        context.parent ! CopyFinished
      }
    }
  }

}
