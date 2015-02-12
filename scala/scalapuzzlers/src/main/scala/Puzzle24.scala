/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle24 extends App{
  def printSorted(a: Array[Double]) {
    util.Sorting.stableSort(a)
    println(a.mkString(" "))
  }

  printSorted(Array(7.89, Double.NaN, 1.23, 4.56))
  printSorted(Array(7.89, 1.23, Double.NaN, 4.56))

  /*
  According to IEEE 754, the Standard for Floating-Point
  Arithmetic, comparisons between a non-NaN value and NaN (with the exception
  of !=) always return false.

  To sort an array, stableSort first divides it down the middle and recursively
  sorts the two subarrays, using Ordering.lt by default to compare
  elements. It then merges these two to produce the final result by taking elements
  from the first subarray as long as they are smaller than or equal to the
  “reference” (initially, the first) element of the second subarray, again according
  to Ordering.lt.

  As per Scala 2.10.0, sorting of floating-point values using
  the default orderings does not correctly handle NaN. Define
  your own orderings, or provide explicit comparison
  functions to sorting algorithms whenever you sort
  floating-point values that may include NaN.
   */
}
