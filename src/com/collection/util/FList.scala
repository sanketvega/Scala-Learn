package com.collection.util

import scala.annotation.tailrec

sealed abstract class FList[+A] {

  def head: A
  
  def tail: FList[A]
  
  def isEmpty: Boolean
  
  def fail(m: String) = throw new NoSuchElementException(m)
  
  def apply(index: Int): A = {
    if(isEmpty)
      fail("")
    else if(index < 0)
      fail("")
    else if(index == 0)
      head
    else
      tail(index - 1)
  }
  
  def prepend[B >: A](item: B): FList[B] = FList.make(item, this)
  
  def append[B >: A](item: B): FList[B] = if(isEmpty) FList.make(item, Nil) else FList.make(this.head, this.tail.append(item)) 
  
  def reverse(): FList[A] = {
    @tailrec
    def reverseTail(list: FList[A], acc: FList[A]): FList[A] = {
      if(list.isEmpty)
        acc
      else
        reverseTail(list.tail, acc.prepend(list.head))
    }
    reverseTail(this, Nil)
  }
  
  def length(): Int = {
    @tailrec
    def tailLength(list: FList[A], acc: Int): Int =  list match{
      case Cons(head, tail) => tailLength(tail, acc + 1)
      case Nil => acc
    }
    tailLength(this, 0)
  }
  
  def foldLeft[B](z: B)(f: (B, A) => B): FList[B] = {
    Nil
  }
  
  def map[B](f: A => B): FList[B] = {
    Nil
  }
  
  def size(): Int = {
    length
  }
  
   override def toString: String = {
    def loop(h: A, t: FList[A], s: String): String = 
      if (!t.isEmpty) loop(t.head, t.tail, s + h + ", ")
      else s + h

    if (isEmpty) "FList()"
    else "FList[" + loop(head, tail, "") + "]"
  }
  
}

case object Nil extends FList[Nothing]{
  
  def head = fail("Empty List")
  
  def tail = fail("Empty List")
  
  def isEmpty = true
}

case class Cons[A](head: A, tail: FList[A]) extends FList[A]{
  
  def isEmpty = false
  
}

object FList{
  
  def make[A](head: A, tail: FList[A]) = Cons(head, tail)
  
  def empty[A]: FList[A] = Nil
  
  def apply[A](items: A*): FList[A] = {
    if(items.isEmpty)
      empty
    else{
       var list: FList[A] = FList.empty
       var index = items.length - 1
       while(index >=0 ){    	   
    	   list = list.prepend(items(index))
    	   index -= 1
       }
       list
    }   
  }
}