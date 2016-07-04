package com.lesson.extra.typeclass

object TypeClassTest extends App{
  
  //interface object test
  import DefaultJsonWriters._
  
  val json: Json = Json.toJson(Person("kishore","kishorenayark@gmail.com"))
  println(json)
  
  
  //interface test
  import JsonSyntax._
  
  val jsonSyntax: Json = Person("kishore","kishorenayark@gmail.com").toJson
  
  println(jsonSyntax)
  
}