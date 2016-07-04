package com.lessons.test

import scala.annotation.tailrec

trait Stack[+A] {
  
  def isEmpty: Boolean
  
  def top: A
  
  def tail: Stack[A]
  
  def push[B >: A](item: B): Stack[B] = FStack.make(item, this)
  
  def pop(): (A, Stack[A]) = (top, tail)
  
  def apply(index: Int): A = {
    if(isEmpty)
      throw new IndexOutOfBoundsException()
    else if(index == 0) top
    else tail(index - 1)
  }
  
  def size: Int = {
    @tailrec
    def tailSize[A](stack: Stack[A], acc: Int): Int = stack match{
      case Node(head, tail) => tailSize(tail, acc + 1)
      case Nil => acc
    }
    tailSize(this, 0)
  }
  
  override def toString() = {
    val size = this.size
    var i = 0;
    val builder = new StringBuilder(" [ ")
    while(i < size){
      builder.append(apply(i)).append(", ")
      i += 1
    }
    builder.deleteCharAt(builder.length-2).append("] ")
    builder.toString()
  }
  
  private def append[B >: A](item: B): Stack[B] = if(isEmpty) FStack.make(item, Nil) else FStack.make(this.top, this.tail.append(item))
  
}

case class Node[A](item: A, next: Stack[A]) extends Stack[A] {
  
  def isEmpty = false
  
  def top = item
  
  def tail = next
}

case object Nil extends Stack[Nothing] {
  
  def isEmpty = true
  
  def top = throw new NoSuchElementException("Top method on empty stack")
  
  def tail = throw new NoSuchElementException("Tail method on empty stack")
  
}


object FStack {
  
  def make[A](head: A, tail: Stack[A]) = Node(head, tail)
  
  
}