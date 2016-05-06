package com.collection.util

trait Iterable[+A] {
  
  def iterator(): Iterator[A]
  
  def forEach[U](f: A => U) = iterator().forEach(f)
  
}