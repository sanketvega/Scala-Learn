package com.funj.collection

/**
 * Base class for Functional Collection
 * 
 * @author kkishore
 */
trait Iterable[+A] {
  
  def iterator(): Iterator[A]
  
  def get: A
  
  def isEmpty: Boolean
  
  def getOption: Option[A]
  
  def map[A, B](f: A => B): Iterable[B]
  
  def forAll(f: A => Boolean): Boolean
  
  def foldLeft[B](acc: B)(op: (B, A) => B): B
  
  def getOrElse[B >: A](default: B) = if(isEmpty) default else get
  
  def forEach[U](f: A => U) = iterator().forEach(f)
  
  def fold[B >: A](acc: B)(op: (B, B) => B): B = foldLeft(acc)(op)
  
}