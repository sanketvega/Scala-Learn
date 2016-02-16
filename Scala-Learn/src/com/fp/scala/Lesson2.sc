package com.fp.scala

import com.collection.util.FList
import scala.annotation.tailrec

object Lesson2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val list = List[Int](1,2,3,4,5,6,7,8,23,10)     //> list  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23, 10)
  
  //Function to implement tail - which removes first element of the list
  def tail[A](list: List[A]): List[A] = list match{
  	case head :: tail => tail
  	case Nil => Nil
  }                                               //> tail: [A](list: List[A])List[A]
  
  tail(list)                                      //> res0: List[Int] = List(2, 3, 4, 5, 6, 7, 8, 23, 10)
  
  //Function to implement setHead which replaces first value of the list
  def setHead[A](list: List[A], headValue: A): List[A] = list match{
  	case head :: tail => headValue :: tail
  	case Nil => Nil
  }                                               //> setHead: [A](list: List[A], headValue: A)List[A]
  
  setHead(list, 100)                              //> res1: List[Int] = List(100, 2, 3, 4, 5, 6, 7, 8, 23, 10)
  
  def dropWhile[A](list: List[A], f: A => Boolean): List[A] = list match{
  	case x :: xs => if(f(x)) dropWhile(xs, f) else list
  	case Nil => Nil
  }                                               //> dropWhile: [A](list: List[A], f: A => Boolean)List[A]
  
  dropWhile(list, (x: Int) => x != 3)             //> res2: List[Int] = List(3, 4, 5, 6, 7, 8, 23, 10)
  
  def drop[A](list: List[A], n: Int): List[A] = {
  	@tailrec
  	def dropTail(list: List[A], counter: Int): List[A] = list match{
  		case head :: tail => if(counter > n - 1) list else dropTail(tail, counter + 1)
  		case Nil => Nil
  	}
  	dropTail(list, 0)
  }                                               //> drop: [A](list: List[A], n: Int)List[A]
  
  drop(list, 0)                                   //> res3: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23, 10)
}