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
		None
	}                                         //> map2: [A, B, C](option1: com.collection.util.error.Option[A], option2: com.c
                                                  //| ollection.util.error.Option[B], f: (A, B) => C)com.collection.util.error.Opt
                                                  //| ion[C]
}