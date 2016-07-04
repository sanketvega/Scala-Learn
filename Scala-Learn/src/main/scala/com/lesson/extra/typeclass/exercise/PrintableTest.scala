package com.lesson.extra.typeclass.exercise

object PrintableTest extends App{
  
  import PrintDefaults._
  
  val format = Print.format("ABCD")
  
  println(format)
  
  val numberFormat = Print.format(34)
  
  println(numberFormat)
  
  var cat = Cat("Jimmy", 5, "white")
  
  println(Print.format(cat))
  
  import PrintSyntax._
  
  cat = Cat("Linda", 7, "black")
  
  println(cat.format)
  
  cat.print
  
}