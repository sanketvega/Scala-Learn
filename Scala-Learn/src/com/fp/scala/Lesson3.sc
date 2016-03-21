package com.fp.scala

import com.collection.util.error._

object Lesson3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //Implementing Variance Function
  def variance(input: Seq[Double]): Option[Double] = {
  	if(input.isEmpty)
  		None
  	else{
  		val mean = input.foldLeft(0.0)(_+_) / input.length
  		val variance = (input.map { x => (x - mean) * (x - mean) }.foldLeft(0.0)(_+_)) / (input.length - 1)
  		Some(variance)
  	}
  }                                               //> variance: (input: Seq[Double])com.collection.util.error.Option[Double]
  
  val list: List[Double] = List(1.0,2.0,12.0,3.0,7.0)
                                                  //> list  : List[Double] = List(1.0, 2.0, 12.0, 3.0, 7.0)
	val result = variance(list)               //> result  : com.collection.util.error.Option[Double] = Some(20.5)
	println(result.getOrElse(0))              //> 20.5
	
	//Generic function map2 that combines two option values using binary function
	def map2[A, B, C](option1: Option[A], option2: Option[B], f: (A, B) => C): Option[C] = {
		if(option1 == None || option2 == None)
			None
		else{
			option1.flatMap { aval => option2.map { bval => f(aval, bval) } }
		}
	}                                         //> map2: [A, B, C](option1: com.collection.util.error.Option[A], option2: com.c
                                                  //| ollection.util.error.Option[B], f: (A, B) => C)com.collection.util.error.Opt
                                                  //| ion[C]
	val option1 = Some(5)                     //> option1  : com.collection.util.error.Some[Int] = Some(5)
	val option2 = Some(10)                    //> option2  : com.collection.util.error.Some[Int] = Some(10)
	val f = (x: Int, y: Int) => x + y         //> f  : (Int, Int) => Int = <function2>
	map2(option1, option2, f)                 //> res0: com.collection.util.error.Option[Int] = Some(15)
	
	/* Write a function sequence that combines a list of "Options" into one "Option" containing a list of all the "Some" values in the original list.
	 If the original list contains "None" even once, the result of the function should be "None" otherwise the result should be "Some"
	 with a list of all the values. Here is its signature */
	 
	
}