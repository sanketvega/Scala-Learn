package com.collection.test

import com.collection.util.FList

object ListTest extends App{
  
  val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](arr:_*)
  println(list.head)
  println(list(3))
  println(list.concat(FList[Int](9,6,4)))
}