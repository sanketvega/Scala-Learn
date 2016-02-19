package com.collection.util

final class Stack[+A](val self: FList[A]) {
  
  def top: A = self.head
  
  def tail: Stack[A] = new Stack(self.tail)
  
  def isEmpty: Boolean = self.isEmpty
  
  def pop: (A, Stack[A]) = (top, tail)
  
  def push[B >: A](item: B): Stack[B] = new Stack(self.append(item))
  
  def toList: FList[A] = self
  
  override def toString = self.toString()
  
}

object Stack {
  
  def empty[A]: Stack[A] = new Stack(Nil)
  
  def apply[A](values: A*): Stack[A] = {
    var list = FList.empty[A]
    var index = 0
    while(index < values.length){
      list = list.append(values(index))
      index += 1
    }
    new Stack(list)
  }
}