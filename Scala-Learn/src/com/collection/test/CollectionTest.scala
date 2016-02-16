package com.collection.test

import com.collection.util.FList
import scala.annotation.tailrec

object CollectionTest extends App{
  
  val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](arr:_*) 
  list.filter { x => x > 2 }.forEach { x => print(x+"\t") }
  println("\n Result : "+list.foldLeft(0)((x, y) => x + y))
  println("\n Result : "+list.foldRight(0)((x, y) => x + y))
  println(list.take(2))
  println(list.drop(3))

  val eList = List[Int](1,2,3,4,5)
  println(eList.take(3))
  
  
  def findFirst[A](array: Array[A], f: A => Boolean): Int = {
  	@tailrec
  	def loop(n: Int): Int = {
  		if( n >= array.length) -1
  		else if( f(array(n)) ) n
  		else loop(n - 1)
  	}
  	loop(array.length - 1)
  } 
  
  val a = findFirst(Array[Int](1,2,3,4,5,6,2,23,1), (x: Int) => x == 3)
  print(a)
}