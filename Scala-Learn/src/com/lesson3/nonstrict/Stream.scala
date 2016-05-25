package com.lesson3.nonstrict

sealed trait Stream[+A] {
  
  def isEmpty(): Boolean
  
  def head(): A
  
  def tail(): Stream[A]
  
  def append[B >: A](element: B): Stream[B] = if(isEmpty()) Stream.cons(element, Empty) else Stream.cons(head(), tail().append(element))
  
  def forEach(f: A => Unit): Unit = {
    if(!isEmpty()){
      f(head())
      tail().forEach { f }
    }
  }
  
  def map[B](f: A => B): Stream[B] = {
    def tailMap(stream: Stream[A], acc: Stream[B]): Stream[B] = stream match{
      case Cons(head, tail) => tailMap(tail(), acc.append(f(head())))
      case Empty => acc
    }
    tailMap(this, Stream.empty[B]())
  }
  
}

final case class Cons[+A](hd: () => A, tl: () => Stream[A]) extends Stream[A] {
  
  def isEmpty() = false
  
  def head() = hd()
  
  def tail() = tl()
}

final case object Empty extends Stream[Nothing]{
  
  def isEmpty() = true
  
  def head() = throw new NoSuchElementException("head() on empty stream")
  
  def tail() = throw new NoSuchElementException("tail() on empty stream")
}

object Stream{
  
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }
  
  def empty[A](): Stream[A] = Empty
  
  def apply[A](elements: A*): Stream[A] = if(elements.isEmpty) empty() else cons(elements.head, apply(elements.tail:_*))
}