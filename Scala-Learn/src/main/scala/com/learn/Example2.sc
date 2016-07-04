package com.learn

object Example2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val list = List(List(1, 2, 3), List(1), List(4, 5, 6))
                                                  //> list  : List[List[Int]] = List(List(1, 2, 3), List(1), List(4, 5, 6))
  
  val newList = list.flatMap { x => x }           //> newList  : List[Int] = List(1, 2, 3, 1, 4, 5, 6)
  
  newList.reduceLeft(_+_)                         //> res0: Int = 22
}