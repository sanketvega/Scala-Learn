package com.funj.collection

/**
 * @author kkishore
 */
trait Traversable[+A] extends Iterable[A] {
  
  self =>
  
  def length: Int
  
  def head: A
  
  def tail: Traversable[A]
  
  def isEmpty(): Boolean = length == 0
  
  def get: A = iterator().next()
  
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