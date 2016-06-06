package com.lessons.test

import com.lesson4.nonstrict.Stream

object StreamTest extends App{
  
  val stream = Stream(5, 4, 3, 2, 4, 1, 7)
  
  println(stream)
  
  stream.map { x => x + 1 }
        .forEach { x => println(x) }
  
  println(stream.takeWhile { x => x > 5 })
}