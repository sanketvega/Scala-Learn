package com.lesson.extra.typeclass.exercise

trait Printable[A] {
  
  def format(value: A): String
  
}

object Print {
  
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)
  
  def print[A](value: A)(implicit printable: Printable[A]): Unit =  println(printable.format(value))
  
}