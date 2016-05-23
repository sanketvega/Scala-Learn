package com.lessons.test

import com.lesson3.collection.util.error._
import com.lesson2.collection._


object CollectionTest extends App {

  val myList: FList[Int] = FList(1, 2, 3, 4, 5, 6)

  val result: Int = myList match {
    case Cons(head, tail) => head + 1
    case Nil              => 0
  }
  
  def reverseList(): FList[Int] = {

		  def tailReverse(acc: FList[Int], list: FList[Int]): FList[Int] = list match{
		    case Cons(head, tail) => tailReverse(acc.prepend(head), tail)
		    case Nil => acc
		  }
		  tailReverse(FList.empty[Int], myList)
  }
  
  def addOne(): FList[Int] = {
 		  def tailReverse(acc: FList[Int], list: FList[Int]): FList[Int] = list match{
		    case Cons(head, tail) => tailReverse(acc.append(head+1), tail)
		    case Nil => acc
		  }
		  tailReverse(FList.empty[Int], myList)
  }
  
  val addOneFun = (x: Int) => x + 1
  var count: Int = 0
  val res = myList.map {x => 
    count += 1
    count
  }
  
  println(count)
  
  println(addOne())

}