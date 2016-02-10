package com.collection.util

import scala.annotation.tailrec

sealed abstract class FList[+A] {

  def head: A
  
  def tail: FList[A]
  
  def isEmpty: Boolean
  
  def fail(m: String) = throw new NoSuchElementException(m)
  
  def prepend[B >: A](item: B): FList[B] = FList.make(item, this)
  
  def append[B >: A](item: B): FList[B] = if(isEmpty) FList.make(item, Nil) else FList.make(this.head, this.tail.append(item))
  
  def length: Int = {
    @tailrec
    def tailLength[A](list: FList[A], acc: Int): Int = list match{
      case Cons(head, tail) => tailLength(tail, acc + 1)
      case Nil => acc
    }
    tailLength(this, 0)
  }
  
  def reverse: FList[A] = {
    @tailrec
    def tailReverse[A](list: FList[A], acc: FList[A]): FList[A] = list match {
      case Cons(head, tail) => tailReverse(tail, acc.prepend(head))
      case Nil => acc
    }
    tailReverse(this, FList.empty[A])
  }
  
  def apply(index: Int): A = {
    if(isEmpty) 
      fail("Index Out of Bound Exception")
    else if(index == 0) head
    else tail(index-1)
  }
  
  def concat[B >: A](list: FList[B]): FList[B] = {
    if(isEmpty)
      list
    else{
      tail.concat(list).prepend(head)
    }
  }
  
  def map[B](f: A => B): FList[B] = {
    @tailrec
    def tailMap(list: FList[A], acc: FList[B]): FList[B] = list match {
      case Cons(head, tail) => tailMap(tail, acc.append(f(head)))
      case Nil => acc      
    }
    tailMap(this, FList.empty[B])
  }
  
  @tailrec
  def forEach(f: A => Unit): Unit = {
    if(!isEmpty){
      f(head)
      tail.forEach(f)
    }
  }
  
  def filter(f: A => Boolean): FList[A] = {
    @tailrec
    def tailFilter(list: FList[A], acc: FList[A]): FList[A] = list match {
      case Cons(head, tail) => tailFilter(tail, if(f(head)) acc.append(head) else acc)
      case Nil => acc
    }
    tailFilter(this, FList.empty[A])
  }
  
  def foldLeft[B](z: B)(op: (B, A) => B): B = {
    @tailrec
    def tailFold(list: FList[A], acc: B): B = list match{
      case Nil => acc
      case Cons(head, tail) => tailFold(tail, op(acc, head))      
    }
    tailFold(this, z)
  }
  
  def foldRight[B](z: B)(op: (A, B) => B): B = {
    reverse.foldLeft(z)((right, left) => op(left, right))    
  }
  
  def reduceLeft[B >: A](op: (B, A) => B): B = {
    if(isEmpty)
      fail("Nil.reduceLeft")
    else 
      tail.foldLeft[B](head)(op)
  }
  
  def reduceRight[B >: A](op: (A, B) => B): B = {
    if(isEmpty)
        fail("Nil.reduceRight")
    else if(tail.isEmpty) head
    else op(head, tail.reduceRight(op))
  }
  
  def take(n: Int): FList[A] = {
    if(isEmpty || n <= 0) Nil
    else if(n > this.length)
      this 
    else {      
      var list = FList.empty[A]
      var index = 0
      while(index < n){
        list = list.append(this(index))
        index += 1
      }
      list
    }
  }
  
  def drop(n: Int): FList[A] = {
    if(isEmpty || n <= 0) Nil
    else if(n > this.length) FList.empty[A]
    else{
      var list = FList.empty[A]      
      //TODO
      list
    }
  }
  
  def iterator(): Iterator[A] =  new FListIterator(this)
  
  override def toString() ={
    val builder = new StringBuilder("[ ");
    var index = 0
    while(index < this.length){
      builder.append(this(index)).append(",")
      index += 1
    }
    builder.deleteCharAt(builder.length - 1).append(" ]")
    builder.toString()
  }
}

case object Nil extends FList[Nothing] {
  
  def head = fail("Head is Empty")
  
  def tail = fail("Tail is Empty")
  
  def isEmpty = true
}

final case class Cons[A](head: A, tail: FList[A]) extends FList[A] {
  
  def isEmpty = false
}

final case class FListIterator[A](list: FList[A]) extends Iterator[A] {
   
  private[this] var index = 0
  
  override def hasNext = index < list.length
  
  override def next: A = {
    val currentIndex = index
    index += 1
    list(currentIndex)
  }  
}

object FList {
  
  def make[A](head: A, tail: FList[A]) = Cons(head, tail)
  
  def empty[A]:FList[A] = Nil
  
  def apply[A](items: A*): FList[A] = {
    if(items.isEmpty)
      empty
    else{
      var emptyList: FList[A] = FList.empty
      var lastIndex = items.length - 1
      while(lastIndex >= 0){
        emptyList = emptyList.prepend(items(lastIndex))
        lastIndex -= 1
      }
      emptyList
    }
  }    
}