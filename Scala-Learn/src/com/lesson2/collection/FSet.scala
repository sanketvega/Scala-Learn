package com.lesson2.collection

sealed abstract class FSet[+A] {
  
  val size: Int
  
  def isEmpty: Boolean
  
}