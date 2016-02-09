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
}

case object Nil extends FList[Nothing] {
  
  def head = fail("Head is Empty")
  
  def tail = fail("Tail is Empty")
  
  def isEmpty = true
}

final case class Cons[A](head: A, tail: FList[A]) extends FList[A] {
  
  def isEmpty = false
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