package com.learn

object Example1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  case class Point(x: Int, y: Int)
  case class Switch(name: String)
  
  val pointObject = Point(5, 4)                   //> pointObject  : com.learn.Example1.Point = Point(5,4)
  
  class TestApp(name: String){ }

  object TestApp {
    def apply(name: String) = new TestApp(name)
  }
  
  val testApp = TestApp("Name")                   //> testApp  : com.learn.Example1.TestApp = com.learn.Example1$$anonfun$main$1$T
                                                  //| estApp$2@19dfb72a
 sealed trait A
  
 case class Sum(x: Int, y: Int) extends A
 
 case class Square(x: Int) extends A
 
 case class Power(x: Double, n: Double) extends A
 
 val testObj: A = new Sum(2, 3)                   //> testObj  : com.learn.Example1.A = Sum(2,3)
 
 testObj match{
 	case Sum(x, y) => println("Sum")
 	case Square(x) => println("Square")
 	case Power(x, y) => println("Power")
 }                                                //> Sum
 
 val list: List[A] = List(Sum(2, 3), Square(5), Power(45.0, 6.0))
                                                  //> list  : List[com.learn.Example1.A] = List(Sum(2,3), Square(5), Power(45.0,6.
                                                  //| 0))
 
 list match{
 	case x :: xs => println(xs)
 	case Nil => println("Empty")
 }                                                //> List(Square(5), Power(45.0,6.0))
}