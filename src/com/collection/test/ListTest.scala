package com.collection.test

import com.collection.util.FList

object ListTest extends App{
  
  val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](arr:_*)
  println(list)
  println(list.length)
  println(list.append(6))
  println(list.prepend(0))
  
}