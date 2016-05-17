package com.funj.collection

/**
 * An custom Iterator class
 * 
 * @author kkishore
 */
trait Iterator[+A] {
  
  def hasNext: Boolean
  
  def next(): A
  
  def isEmpty = !hasNext
  
  def forEach[U](f: A => U) = { while(hasNext) { f(next) } }
}

abstract class AbstractIterator[+A] extends Iterator[A]