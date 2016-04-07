package com.fp.scala


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
  }                                               //> variance: (input: Seq[Double])Option[Double]
  
  val list: List[Double] = List(1.0,2.0,12.0,3.0,7.0)
                                                  //> list  : List[Double] = List(1.0, 2.0, 12.0, 3.0, 7.0)
	val result = variance(list)               //> result  : Option[Double] = Some(20.5)
	println(result.getOrElse(0))              //> 20.5
	
	//Generic function map2 that combines two option values using binary function
	def map2[A, B, C](option1: Option[A], option2: Option[B], f: (A, B) => C): Option[C] = {
		if(option1 == None || option2 == None)
			None
		else{
			option1.flatMap { aval => option2.map { bval => f(aval, bval) } }
		}
	}                                         //> map2: [A, B, C](option1: Option[A], option2: Option[B], f: (A, B) => C)Optio
                                                  //| n[C]
	val option1 = Some(5)                     //> option1  : Some[Int] = Some(5)
	val option2 = Some(10)                    //> option2  : Some[Int] = Some(10)
	val f = (x: Int, y: Int) => x + y         //> f  : (Int, Int) => Int = <function2>
	map2(option1, option2, f)                 //> res0: Option[Int] = Some(15)
	
	/* Write a function sequence that combines a list of "Options" into one "Option" containing a list of all the "Some" values in the original list.
	 If the original list contains "None" even once, the result of the function should be "None" otherwise the result should be "Some"
	 with a list of all the values. Here is its signature */
	 
	 def sequence[A](a: List[Option[A]]): Option[List[A]] = {
	 	Option(a.map { x => x.get }.toList)
	 }                                        //> sequence: [A](a: List[Option[A]])Option[List[A]]
	 
	 val sequenceList: List[Option[Int]] = List(Some(5), Some(6))
                                                  //> sequenceList  : List[Option[Int]] = List(Some(5), Some(6))
	 sequence(sequenceList)                   //> res1: Option[List[Int]] = Some(List(5, 6))
	
	/*Wanting to sequence the results of a map this way is a common enough occurrence to
	 warrant a new generic function, traverse, with the following signature:
	*/
	def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
		Option(a map { x => f(x).get })
	}                                         //> traverse: [A, B](a: List[A])(f: A => Option[B])Option[List[B]]
	
	traverse(list)((x: Double) => Some(x))    //> res2: Option[List[Double]] = Some(List(1.0, 2.0, 12.0, 3.0, 7.0))
}