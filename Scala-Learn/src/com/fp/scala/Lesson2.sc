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
  
  //Drop While Function
  def dropWhile[A](list: List[A], f: A => Boolean): List[A] = list match{
  	case x :: xs => if(f(x)) dropWhile(xs, f) else list
  	case _ => list
  }                                               //> dropWhile: [A](list: List[A], f: A => Boolean)List[A]
  
  dropWhile(list, (x: Int) => x != 3)             //> res2: List[Int] = List(3, 4, 5, 6, 7, 8, 23, 10)
  
  //Drop first n items
  def drop[A](list: List[A], n: Int): List[A] = {
  	@tailrec
  	def dropTail(list: List[A], counter: Int): List[A] = list match{
  		case head :: tail => if(counter > n - 1) list else dropTail(tail, counter + 1)
  		case Nil => Nil
  	}
  	dropTail(list, 0)
  }                                               //> drop: [A](list: List[A], n: Int)List[A]
  
  drop(list, 0)                                   //> res3: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23, 10)
  
  //Append list to another list
  def append[A](list: List[A], appendList: List[A]): List[A] = list match{
  	case head :: tail => head :: append(tail, appendList)
  	case Nil => appendList
  }                                               //> append: [A](list: List[A], appendList: List[A])List[A]
  
  append(list, List(100,2,90))                    //> res4: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23, 10, 100, 2, 90)
  
  //init method get all elements of a list except last element
  def init[A](list: List[A]): List[A] = list match{
  	case head :: Nil => Nil
  	case head :: tail => head :: init(tail)
  	case Nil => Nil
  }                                               //> init: [A](list: List[A])List[A]
  
  init(list)                                      //> res5: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23)
  
  //Create a foldLeft method
  def foldLeft[A, B](list: List[A])(z: B)(op: (B, A) => B): B = {
  	def tailFold(list: List[A], acc: B): B = list match{
  		case head :: tail => tailFold(tail, op(acc, head))
  		case Nil => acc
  	}
  	tailFold(list, z)
  }                                               //> foldLeft: [A, B](list: List[A])(z: B)(op: (B, A) => B)B
  
  foldLeft(list)(0)(_+_)                          //> res6: Int = 69
  
  //Create a foldRight method
  def foldRight[A, B](list: List[A])(z : B)(op: (A, B) => B): B = {
  	foldLeft(list.reverse)(z)((right, left) => op(left, right))
  }                                               //> foldRight: [A, B](list: List[A])(z: B)(op: (A, B) => B)B
  
  foldRight(list)(0)(_+_)                         //> res7: Int = 69
  
  //Compute length using foldLeft method
  def length[A](list: List[A]): Int = foldLeft(list)(0)((x: Int, y: A) => x + 1)
                                                  //> length: [A](list: List[A])Int
  
  length(list)                                    //> res8: Int = 10

	//Compute sum using foldLeft method
	def sum(list: List[Int]): Int = foldLeft(list)(0)(_+_)
                                                  //> sum: (list: List[Int])Int
	
	sum(list)                                 //> res9: Int = 69
	////Compute product using foldLeft method
	def product(list: List[Int]): Double = foldLeft(list)(1)(_*_)
                                                  //> product: (list: List[Int])Double
	product(list)                             //> res10: Double = 9273600.0
	
	//Implement map method
	def map[A, B](list: List[A], f: A => B): List[B] = {
		def tailMap(acc: List[B], list: List[A]): List[B] = list match {
			case head :: tail => tailMap(acc ::: List(f(head)), tail)
			case Nil => acc
		}
		tailMap(List.empty[B], list)
	}                                         //> map: [A, B](list: List[A], f: A => B)List[B]
	
	map(list, (x: Int) => x + 1)              //> res11: List[Int] = List(2, 3, 4, 5, 6, 7, 8, 9, 24, 11)
	
	val doubleList: List[Double] = List(1.0,2.0,3.0)
                                                  //> doubleList  : List[Double] = List(1.0, 2.0, 3.0)
	
	map(doubleList, (x: Double) => String.valueOf(x))
                                                  //> res12: List[String] = List(1.0, 2.0, 3.0)
	//Create a filter method
	def filter[A](list: List[A], f: A => Boolean): List[A] = {
	
		def tailFilter(acc: List[A], list: List[A]): List[A] = list match{
			case head :: tail => if(f(head)) tailFilter(acc ::: List(head), tail) else tailFilter(acc, tail)
			case Nil => acc
		}
		tailFilter(List.empty, list)
	}                                         //> filter: [A](list: List[A], f: A => Boolean)List[A]
	
	filter(list, (x: Int) => x > 7)           //> res13: List[Int] = List(8, 23, 10)
	
	//Implement list reverse method using foldLeft
	foldLeft(list)(List.empty[Int])((x : List[Int], y: Int) => y :: x)
                                                  //> res14: List[Int] = List(10, 23, 8, 7, 6, 5, 4, 3, 2, 1)

	def appendWithFoldLeft[A](list: List[A], appendList: List[A]): List[A] = foldLeft(appendList)(list)((x: List[A], y : A) => x ::: List(y))
                                                  //> appendWithFoldLeft: [A](list: List[A], appendList: List[A])List[A]

	val appList = List(100,200,300,400,500)   //> appList  : List[Int] = List(100, 200, 300, 400, 500)
	
	appendWithFoldLeft(list, appList)         //> res15: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 23, 10, 100, 200, 300, 400,
                                                  //|  500)
}