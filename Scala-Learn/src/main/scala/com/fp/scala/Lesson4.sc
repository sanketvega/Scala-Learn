package com.fp.scala

object Lesson4 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  import com.lesson4.nonstrict._
  val stream = Stream(1,3, 5, 2, 5, 6, 1, 6, 3,2) //> stream  : com.lesson4.nonstrict.Stream[Int] = Stream(1, ?)
  val list = stream.toList()                      //> list  : List[Int] = List(1, 3, 5, 2, 5, 6, 1, 6, 3, 2)
  
  val size = stream.length()                      //> size  : Int = 10
  
  val newStream = stream.take(4).toList()         //> newStream  : List[Int] = List(1, 3, 5, 2)
  
  val dropStream = stream.drop(10).toList()       //> dropStream  : List[Int] = List()
  
  val dropWhileStream = stream.takeWhile { x => x < 4 }.toList()
                                                  //> dropWhileStream  : List[Int] = List(1, 3)
  
  val forAllStream = stream.forAll { x => x % 2 == 0 }
                                                  //> forAllStream  : Boolean = false
	val sum = stream.foldRight(0)(_+_)        //> sum  : Int = 34
	
	
}