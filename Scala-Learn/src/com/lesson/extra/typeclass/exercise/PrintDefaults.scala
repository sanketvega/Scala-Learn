package com.lesson.extra.typeclass.exercise

object PrintDefaults {
  
  implicit val stringPrint = new Printable[String] {
    
    def format(value: String): String  = value
    
  }
  
  implicit val intPrint = new Printable[Int] {
    
    def format(value: Int): String = String.valueOf(value)
    
  }
  
  implicit val catPrintable = new Printable[Cat] {
    
    def format(value: Cat): String = value.name+" is a "+String.valueOf(value.age)+" year-old "+value.color+" cat."
  
  }
  
}