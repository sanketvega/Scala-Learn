package com.lesson5.funp

case class SimpleRNG(seed: Long) extends RNG {
  
  def nextInt:(Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n: Int = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
  
}