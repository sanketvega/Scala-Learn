package com.lesson5.funp

trait RNG {
  
  def nextInt(): (Int, RNG)
}