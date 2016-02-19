package com.collection.test

import com.collection.util.FList
import scala.annotation.tailrec
import com.collection.util.Nil
import com.collection.util.Cons

object CollectionTest extends App{
  
  val arr: Array[Int] = (1 to 5).toArray
  val list = FList[Int](arr:_*) 
  println(list)
  list.filter { x => x > 2 }.forEach { x => print(x+"\t") }
  println("\n Result : "+list.foldLeft(0)((x, y) => x + y))
  println("\n Result : "+list.foldRight(0)((x, y) => x + y))
  println(list.take(2))
  println(list.drop(3))
  println(list.init())

  val eList = List[Int](1,2,3,4,5)
  println(eList.drop(3))
  
  def sum(ints: FList[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
}
  
  val x = FList(1,2,3,4,5) match {    
    case Cons(x, Cons(2, Cons(4, _))) => x
		case Nil => 42
		case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
		case Cons(h, t) => h + sum(t)
		case _ => 101
	}
  
  println(x)
}