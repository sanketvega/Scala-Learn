package com.fp.scala

import scala.annotation.tailrec

object Lesson1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //Implementing Fibonnaci series using tail recursion
  
  def fib(n: Int): Int = {
  	@tailrec
  	def fibTail(n: Int, prev: Int, current: Int): Int = {
  		if(n == 0)
  			prev
  		else{
  			println(prev)
  			fibTail(n-1, current, prev + current)
  		}
  	}
  	fibTail(n, 0, 1)
  }                                               //> fib: (n: Int)Int
  
  fib(5)                                          //> 0
                                                  //| 1
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| res0: Int = 5
  
  //Implementing Factorial of a Number
  def fact(n: Int): Int = {
  	@tailrec
  	def tailFact(n: Int, acc: Int): Int = if(n <=0) acc else tailFact(n-1, acc * n)
  	tailFact(n, 1)
  }                                               //> fact: (n: Int)Int
  
  fact(5)                                         //> res1: Int = 120
  
  //Function to Find Element in Array
  def findFirst[A](array: Array[A], f: A => Boolean): Int = {
  	@tailrec
  	def loop(n: Int): Int = {
  		if( n >= array.length) -1
  		else if( f(array(n)) ) n
  		else loop(n + 1)
  	}
  	loop(0)
  }                                               //> findFirst: [A](array: Array[A], f: A => Boolean)Int
 
 	findFirst(Array[Int](1,2,3,4,5,6,2,23,1), (x: Int) => x == 3)
                                                  //> res2: Int = 2

	def isSorted[A](array: Array[A], ordered: (A, A) => Boolean): Boolean = {
		def loop(n: Int): Boolean = {
			if( n >= array.length-1) true
			else if( ordered(array(n), array(n+1))) false
			else loop(n+1)
			
		}
		loop(0)
	}                                         //> isSorted: [A](array: Array[A], ordered: (A, A) => Boolean)Boolean
	
	isSorted(Array[Int](1,2,3,4,5), (x: Int, y: Int) => x > y)
                                                  //> res3: Boolean = true
}