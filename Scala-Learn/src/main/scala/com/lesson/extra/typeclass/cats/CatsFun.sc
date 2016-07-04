package com.lesson.extra.typeclass.cats

import com.lesson.extra.typeclass.Person

object CatsFun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  case class Person(name: String)
  import cats.std.int._
  import cats.Eq
  
  val eqInt = Eq[Int]                             //> eqInt  : cats.kernel.Eq[Int] = cats.kernel.std.IntOrder@675d3402
	eqInt.eqv(5, 4)                           //> res0: Boolean = false
	
	import cats.syntax.eq._
	5 === 4                                   //> res1: Boolean = false
	6 =!= 10                                  //> res2: Boolean = true
	
	import java.util.Date
	
	implicit val dateEq = Eq.instance[Date]{ (date1, date2) =>
		date1.getTime == date2.getTime
	}                                         //> dateEq  : cats.kernel.Eq[java.util.Date] = cats.kernel.Eq$$anon$115@457e2f02
                                                  //| 
	val d1 = new Date()                       //> d1  : java.util.Date = Mon Jul 04 14:52:31 IST 2016
	
	d1 === d1                                 //> res3: Boolean = true
	
	implicit val personEq = Eq.instance[Person]{ (person1, person2) =>
		person1.name == person2.name
	}                                         //> personEq  : cats.kernel.Eq[com.lesson.extra.typeclass.cats.CatsFun.Person] =
                                                  //|  cats.kernel.Eq$$anon$115@6767c1fc
	val p1 = Person("Kishore")                //> p1  : com.lesson.extra.typeclass.cats.CatsFun.Person = Person(Kishore)
	val p2 = Person("Kishore")                //> p2  : com.lesson.extra.typeclass.cats.CatsFun.Person = Person(Kishore)
	
	p1 === p2                                 //> res4: Boolean = true
}