package com.lessons.test

import com.lesson2.collection._

object Main extends App{
  
  val tree: Tree[Int] = Branch(Branch(Leaf(5), Leaf(4)), Branch(Leaf(6), Branch(Leaf(17), Leaf(10))))
  println(tree.size)
  println(tree.maximum())
  println(tree.depth())
  
  println(tree)
  
  println(tree.map { x => x + 1 })
}