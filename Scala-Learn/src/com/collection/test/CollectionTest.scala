package com.collection.test

import com.collection.util.FList

object CollectionTest extends App{
  
  //val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](1,2,3,4,5,6,7,8,9,10) 
  println(list)
  println(list.foldLeft(0)(_+_))
  
  println(list.map { x => x.toDouble }.foldLeft(0.0)(_+_));

}