package com.lessons.test

import com.lesson3.nonstrict.Stream

object StreamTest extends App{
  
  val stream = Stream(5, 4, 3, 2, 4, 1, 7)
  
  println(stream)
  
  stream.map { x => x + 1 }
        .forEach { x => println(x) }
  
}