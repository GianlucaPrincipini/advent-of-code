package twentytwo

import scala.math._


object Day8 extends Challenge[Long] {
  // a tree is a number with position in grid (n, y, x) 
  type Tree = (Int, Int, Int)
  type Grid = Array[Array[Tree]]

  override def part1(input: List[String]): Long = {
    val grid = parseGrid(input)
    grid.flatMap(_.map(isVisible(_, grid))).count(_.self)
  }

  override def part2(input: List[String]): Long = {
    val grid = parseGrid(input)
    grid
      .flatMap(row => row
        .filter(tree => !isAtBorder(tree, grid))
        .map(element => calculateScenicScore(element, grid)
        )
      ).max
  }

  def parseGrid(input: List[String]): Grid =
    input.zipWithIndex.map { case (row, i) => row.toCharArray.zipWithIndex.map { case (char, j) => (char.toString.toInt, i, j) } }.toArray

  def treesAround(grid: Grid, x: Int, y: Int): List[List[Tree]] = {
    List(
      grid.take(y).map(_(x)).reverse.toList, // North
      grid.drop(y + 1).map(_(x)).toList, // South
      grid(y).take(x).reverse.toList, // West
      grid(y).drop(x + 1).toList // East
    )
  }

  def isVisible(tree: Tree, grid: Grid): Boolean = {
    treesAround(grid, tree._3, tree._2)
      .exists(portion => isHighest(tree, portion))
  }

  def calculateScenicScore(tree: Tree, grid: Grid): Long = {
    tree match {
      case (_, 0, _) => 0
      case (_, _, 0) => 0
      case _ => treesAround(grid, tree._3, tree._2)
        .map(line => min(line.length, line.takeWhile(_._1 < tree._1).length + 1))
        .product
    }
  }

  def isHighest(tree: Tree, gridPortion: List[Tree]): Boolean = 
    gridPortion.forall(_._1 < tree._1)

  def isAtBorder(tree: Tree, grid: Grid) =
    tree._2 == grid.length - 1 || tree._2 == 0 || tree._3 == grid.head.length - 1 || tree._3 == 0

  def main(args: Array[String]): Unit = {
    this.printResult(2022, 8)
  }
}