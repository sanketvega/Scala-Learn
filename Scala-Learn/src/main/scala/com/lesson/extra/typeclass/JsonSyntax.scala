package com.lesson.extra.typeclass

object JsonSyntax {
  
  implicit class JsonOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]): Json = writer.write(value)
  }
}