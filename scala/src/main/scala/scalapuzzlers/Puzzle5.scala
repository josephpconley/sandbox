package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle5 extends App{
  def sumSizes(collections: Iterable[Iterable[_]]): Int = collections.map(_.size).sum

//  println(sumSizes(List(Set(1, 2), List(3, 4))))
//  println(sumSizes(Set(List(1, 2), Set(3, 4))))

  //mapping over a set returns a set, so Set(2, 2) = Set(2)

  //how to write generic function for collection?  one solution would be to call toSeq
  def sumSizes2(collections: Iterable[Iterable[_]]): Int = collections.toSeq.map(_.size).sum

//  println(sumSizes2(List(Set(1, 2), List(3, 4))))
//  println(sumSizes2(Set(List(1, 2), Set(3, 4))))

  //EVEN BETTER, using fold we don't need to worry about containers
  def sumSizes3(collections: Iterable[Iterable[_]]): Int = collections.foldLeft(0) { (sumOfSizes, collection) =>
    sumOfSizes + collection.size
  }

  println(sumSizes3(List(Set(1, 2), List(3, 4))))
  println(sumSizes3(Set(List(1, 2), Set(3, 4))))
}
