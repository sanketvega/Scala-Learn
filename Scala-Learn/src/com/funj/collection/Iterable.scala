package com.funj.collection

/**
 * Base class for Functional Collection
 * 
 * @author kkishore
 */
trait Iterable[+A] {
  
  def iterator(): Iterator[A]
  
  def forEach[U](f: A => U) = iterator().forEach(f)
  
  def get: A
  
  def isEmpty: Boolean
  
  def getOption: Option[A]
  
  def getOrElse[B >: A](default: B) = if(isEmpty) default else get
  
}