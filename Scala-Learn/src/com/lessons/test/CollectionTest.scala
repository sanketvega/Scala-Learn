package com.lessons.test

import com.lessons.test.FStack._
import scala.None

object CollectionTest extends App {

 var stack: Stack[Int] = FStack.make(5, FStack.make(6, Nil))
 
 stack = stack.push(10)
 
 println(stack)
 
 println(stack.pop()._2)
 
 
}