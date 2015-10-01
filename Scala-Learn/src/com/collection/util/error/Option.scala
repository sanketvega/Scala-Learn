package com.collection.util.error

trait Option[+A] {  
  def isEmpty: Boolean
  
  def get: A
  
  def getOrElse[B >: A](default: B) = {
    if(isEmpty)
      default
    else
      get
  }

}

final case class Some[A](value: A) extends Option[A]{
  
  def isEmpty = false
  
  def get: A = value
  
}

case object Nil extends Option[scala.Nothing]{
  
  def isEmpty = true
  
  def get = throw new NoSuchElementException("Nil.get")
  
}