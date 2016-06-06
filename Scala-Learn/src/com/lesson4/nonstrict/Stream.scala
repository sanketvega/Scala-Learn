package com.lesson4.nonstrict

import scala.annotation.tailrec

sealed trait Stream[+A] {
  
  def isEmpty(): Boolean
  
  def head(): A
  
  def tail(): Stream[A]
  
  def append[B >: A](element: B): Stream[B] = if(isEmpty()) Stream.cons(element, Empty) else Stream.cons(head(), tail().append(element))
  
  def apply(index: Int): A = {
    if(isEmpty) 
      throw new IndexOutOfBoundsException("Index Out of Bound Exception")
    else if(index == 0) head()
    else tail()(index-1)
  }
  
  def forEach(f: A => Unit): Unit = {
    if(!isEmpty()){
      f(head())
      tail().forEach { f }
    }
  }
  
  def map[B](f: A => B): Stream[B] = {
    @tailrec
    def tailMap(stream: Stream[A], acc: Stream[B]): Stream[B] = stream match{
      case Cons(head, tail) => tailMap(tail(), acc.append(f(head())))
      case Empty => acc
    }
    tailMap(this, Stream.empty[B]())
  }
  
  def toList(): List[A] = {
    @tailrec
    def tailtToList(stream: Stream[A], acc: List[A]): List[A] = stream match {
      case Cons(head, tail) => tailtToList(tail(),  acc ::: List(head()))
      case Empty => acc
    }
    tailtToList(this, List.empty[A]) 
  }
  
  def take(n: Int): Stream[A] = {
    @tailrec
    def tailTake(stream: Stream[A], acc: Stream[A], n: Int): Stream[A] = stream match{
      case Cons(head, tail) => if(n > 0) tailTake(tail(), acc.append(head()), n - 1) else acc
      case Empty => acc      
    }
    if(n == length()) 
      this
    else
      if(n > 0) tailTake(this, Stream.empty[A](), n) else Stream.empty[A]()    
  }
  
  def drop(n: Int): Stream[A] = {
    @tailrec
    def tailDrop(acc: Stream[A], n: Int): Stream[A] = acc match{
      case Cons(head, tail) => if(n > 0 ) tailDrop(tail(), n - 1) else acc
      case Empty => acc
    }
    if(n == this.length())
      Stream.empty[A]()
    else
      if(n > 0) tailDrop(this, n) else this
  }
  
  def takeWhile(f: A => Boolean): Stream[A] = {
    @tailrec
    def tailTakeWhile(stream: Stream[A], acc: Stream[A]): Stream[A] = stream match{
      case Cons(head, tail) => if(f(head())) tailTakeWhile(tail(), acc.append(head())) else acc
      case Empty => acc      
    }
    tailTakeWhile(this, Stream.empty[A]())
  }
  
  def forAll(f: A => Boolean): Boolean = {
    val size = length()
    this.takeWhile { f }.length() == size 
  }
  
  def length(): Int = {
    @tailrec
    def tailLength(stream: Stream[A], acc: Int): Int = stream match{
      case Cons(head, tail) => tailLength(tail(), acc + 1)
      case Empty => acc
    }
    tailLength(this, 0)
  }
  
  def foldLeft[B](z: => B)(op: (=> B, A) => B): B = {
    def tailFoldLeft(stream: Stream[A], acc: B): B = stream match {
      case Cons(head, tail) => tailFoldLeft(tail(), op(acc, head()))
      case Empty => acc
    }
    tailFoldLeft(this, z)
  }
  
  def foldRight[B](z: => B)(f: (A,  => B) => B): B = this match{
    case Cons(head, tail) => f(head(), tail().foldRight(z)(f))
    case _ => z
  }
  
  def foldRightTakeWhile(f: A => Boolean): Stream[A] = {
    foldRight(Stream.empty[A]())((head, tail) => {
      if(f(head)) Stream.cons(head, tail) else Stream.empty[A]()
    })
  }
  
  def headOption(): Option[A] = foldRight(None: Option[A])((h, _) => Some(h))
  
  def foldMap[B](f: A => B): Stream[B] = foldRight(Stream.empty[B]())((h, t) => {
    Stream.cons(f(h), t)
  })
  
  def foldFilter(f: A => Boolean): Stream[A] = foldRight(Stream.empty[A]())((h, t) => {
    if(f(h)) Stream.cons(h, t) else t
  })
  
  def append[B >: A](stream: => Stream[B]): Stream[B] = foldRight(stream)((h, t) => Stream.cons(h, t))

  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(Stream.empty[B]())((h, t) => f(h) append(t))
  
  def constant[A](value: A): Stream[A] = Stream.cons(value, constant(value))
  
  def from(n: Int): Stream[Int] = Stream.cons(n, from(n + 1))
  
  def fib(start: Int, end: Int): Stream[Int] = Stream.cons(start, fib(end, start + end)) 
  
  override def toString(): String = if(isEmpty()) "Stream()" else "Stream("+head()+", ?)"
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