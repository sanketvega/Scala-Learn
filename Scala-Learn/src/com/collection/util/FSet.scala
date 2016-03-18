package com.collection.util

sealed abstract class FSet[+A] {
  
  val size: Int
  
  def isEmpty: Boolean
  
}