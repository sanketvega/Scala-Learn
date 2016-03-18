package com.collection.test

import com.collection.util.FList
import scala.collection.immutable.Set
import scala.collection.immutable.HashSet
import scala.collection.immutable.List

object CollectionTest extends App{
  
  //val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](1,2,3,4,5,6,7,8,9,10) 
  println(list)
  println(list.foldLeft(0)(_+_))
  
  println(list.map { x => x.toDouble }.foldLeft(0.0)(_+_));
  
  var set = new HashSet[Int]()
  set = set + 1
  set.iterator
  
  val l = List(1,2,3,4,5)
  l.iterator
}