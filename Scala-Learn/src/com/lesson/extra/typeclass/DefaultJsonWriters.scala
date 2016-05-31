package com.lesson.extra.typeclass

object DefaultJsonWriters {
  
  implicit val stringJsonWriter = new JsonWriter[String] {
    
   def write(value: String):Json =  JsonString(value)
  
  }
  
  implicit val personJsonWriter = new JsonWriter[Person] {
    
    def write(person: Person):Json = JsonObject(Map("name" -> JsonString(person.name), "email" -> JsonString(person.email)))
  }
  
}