package com.lesson.extra.typeclass

sealed trait Json 

final case class JsonObject(get: Map[String, Json]) extends Json

final case class JsonString(get: String) extends Json

final case class JsonNumber(get: Double) extends Json

trait JsonWriter[A]{
  
  def write(value: A): Json

}

object Json{
  
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = writer.write(value)
  
}