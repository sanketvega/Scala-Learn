package com.collection.util.error

sealed trait Either[+E, +A] 

final case class Left[+E](value: E) extends Either[E, Nothing]

final case class Right[+A](value: A) extends Either[Nothing, A]