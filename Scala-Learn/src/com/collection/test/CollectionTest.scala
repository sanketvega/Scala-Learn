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
  
}