package com.lesson.extra.typeclass.cats

import cats.implicits._
import cats.Show

object CatsLibExample {
  
  def main(ags: Array[String]){
    import cats.syntax.show._
    val shownInt = 123.show
    println(shownInt)
    import java.util.Date
    implicit val showDate = Show.show[Date] { x => s"It's been ${x.getTime} milliseconds since the epoch" }
    val showRes = new Date().show
    println(showRes)
  }
  
}