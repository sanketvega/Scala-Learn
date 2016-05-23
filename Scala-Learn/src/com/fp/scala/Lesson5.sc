package com.fp.scala

import com.lesson6.funp.SimpleRNG

object Lesson6 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val simpleRng = SimpleRNG(40)                   //> simpleRng  : com.lesson6.funp.SimpleRNG = SimpleRNG(40)
  
  val result1 = simpleRng.nextInt()               //> result1  : (Int, com.lesson6.funp.RNG) = (15389956,SimpleRNG(1008596156691))
                                                  //| 
  val result2 = simpleRng.nextInt()               //> result2  : (Int, com.lesson6.funp.RNG) = (15389956,SimpleRNG(1008596156691))
                                                  //| 
                                                  
  
}