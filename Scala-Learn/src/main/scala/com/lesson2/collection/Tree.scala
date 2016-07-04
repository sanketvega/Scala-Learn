package com.lesson2.collection

sealed trait Tree[+A] {
  
  def size(): Int = this match {    
    case Leaf(a) => 1
    case Branch(a, b) => 1 + a.size + b.size()
  }
  
  def maximum(): Int = this match {
    case Leaf(a) => a.asInstanceOf[Int]
    case Branch(a, b) => a.maximum() max b.maximum()
  }
  
  def depth(): Int = this match {
    case Leaf(_) => 0
    case Branch(a, b) => 1 + a.depth() max b.depth();
  }
  
  def map[B](f: A => B): Tree[B] = this match {
    case Leaf(a) => Leaf(f(a))
    case Branch(a, b) => Branch(a.map { f }, b.map { f })
  }
  
  def fold[B](f: A => B)(op: (B, B) => B): B = this match {
    case Leaf(a) => f(a)
    case Branch(a, b) => op(a.fold(f)(op), b.fold(f)(op))
  }
  
  def sizeViaFold(): Int = this.fold((x: A) => 1)(1+_+_)
  
  def maxViaFold(): Int = this.fold(x => x.asInstanceOf[Int])(_ max _)
  
  def depthViaFold(): Int = this.fold(a => 0)((l, r) => 1 + (l max r))
  
  def mapViaFold[B](f: A => B) = this.fold(x => Leaf(f(x)): Tree[B])(Branch(_, _))
}

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]