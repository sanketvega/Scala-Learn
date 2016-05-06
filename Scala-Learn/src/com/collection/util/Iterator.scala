package com.collection.util

trait Iterator[+A] {
  
  self =>
  
  def hasNext: Boolean
  
  def next(): A
  
  def isEmpty = !hasNext
  
  def forEach[U](f: A => U) = { while(hasNext) { f(next) } }
  
  def take(n: Int): Iterator[A] = {
    if(n > 0){
      var i = n;
      while( i > 0 && self.hasNext){
        self.next
        i -= 1
      }
      new AbstractIterator[A]{
        def hasNext = self.hasNext        
        def next = self.next
      }
    }else{
      new AbstractIterator[Nothing]{
        def hasNext = false        
        def next(): Nothing = throw new NoSuchElementException("Empty Iterator")
      }
    }
  }
  
  def drop(n: Int): Iterator[A] = {
    var i = 0
    while(i < n && hasNext){
      next()
      i += 1
    }
    this
  }
  
  def map[B](f: A => B): Iterator[B] = new AbstractIterator[B] {
    def hasNext = self.hasNext    
    def next = f(self.next())
  }
}

abstract class AbstractIterator[+A] extends Iterator[A]

object Iterator{
  
}