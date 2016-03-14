package com.collection.test

import com.collection.util.error._

object OptionTest extends App{
  
  def variance(input: Seq[Double]): Option[Double] = {
  	if(input.isEmpty)
  		None
  	else{
  		val mean = input.foldLeft(0.0)(_+_) / input.length
  		val variance = input.map { x => Math.pow(x - mean, 2) }.foldLeft(0.0)(_+_)
  		val a = (variance) / (input.length -1) 
  		Some(variance)
  	}
  }                                               //> variance: (input: Seq[Double])com.collection.util.error.Option[Double]
  
  val list: List[Double] = List(1.0,2.0,12.0,3.0,7.0)
                                                  //> list  : List[Double] = List(1.0, 2.0, 12.0, 3.0, 7.0)
	val result = variance(list)
}