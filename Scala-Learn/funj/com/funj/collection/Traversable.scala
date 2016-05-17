package com.funj.collection

/**
 * @author kkishore
 */
trait Traversable[+A] extends Iterable[A] {
  
  self =>
  
  def length: Int
  
  def head(): A
  
  def tail(): Traversable[A]
  
  def init(): Traversable[A]
  
  def filter(f: A => Boolean): Traversable[A]
  
  def find(f: A => Boolean): Option[A]
  
  def distinct(): Traversable[A]
  
  def drop(n: Int): Traversable[A]
  
  def dropWhile(f: A => Boolean): Traversable[A]
  
  def dropUntil(f: A => Boolean): Traversable[A]
  
  def take(n: Int): Traversable[A]
  
  def takeWhile(f: A => Boolean): Traversable[A]
  
  def takeUntil(f: A => Boolean): Traversable[A]
  
  def last(): A = {
    if(isEmpty)
      throw new NoSuchElementException("Empty Collection, Invalid Operation")
    else{
      var item: A = head()
      val iter = iterator();
      while(iter.hasNext){
        item = iter.next()
      }
      item
    }
  }
  
  def isEmpty(): Boolean = length == 0
  
  def get: A = iterator().next()
  
  def foldLeft[B](acc: B)(op: (B, A) => B): B  = {
    var result = acc
    this.forEach { x => result = op(result, x) }
    result
  }
  
  override def iterator(): Iterator[A] = {
    new AbstractIterator[A] {
      
      var iter = self;
      
      override def hasNext(): Boolean =  !iter.isEmpty()
      
      override def next(): A = {
        val item: A = iter.head
        iter = iter.tail
        item
      }      
    }
  }
  
}