package com.lesson3.collection.util.error

sealed trait Option[+A]{
  
  def isEmpty: Boolean
  
  def get: A
  
  def getOrElse[B >: A](default: => B): B = if(isEmpty) default else this.get
  
  def map[B](f: A => B): Option[B] = if(isEmpty) None else Some(f(this.get))
  
  def flatMap[B](f: A => Option[B]): Option[B] = if(isEmpty) None else f(this.get)
  
  def filter(f: A => Boolean): Option[A] = if(!isEmpty && f(this.get)) this else None
  
  def forEach[U](f: A => U) = if(!isEmpty) f(this.get)
  
  def exists(f: A => Boolean): Boolean = !isEmpty && f(this.get)
}

final case object None extends Option[Nothing]{
  
  def isEmpty = true;
  
  def get = throw new Error("Invalid Operation for empty collection")
}

final case class Some[A](value: A) extends Option[A] {
  
  def isEmpty = false
  
  def get = value
}