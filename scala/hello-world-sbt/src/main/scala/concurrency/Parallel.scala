package concurrency


object Parallel extends App{
  def time(f : () => Unit) = {
    val t1 = System.currentTimeMillis()
    f()
    println("Elapsed: " + ((System.currentTimeMillis() - t1)/1.0e3));
  }

  def isPrime(x : Int) : Boolean = {
    for (i <- 2 until x) {
      if ((x % i) == 0) {
        return true
      }
    }
    return false
  }

//  time(() => { (1 to 100000). par.filter(isPrime) } )

  time(() => { (1 to 100000).filter(isPrime) } )
}
