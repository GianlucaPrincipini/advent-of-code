package twentytwo

import scala.annotation.tailrec

object Day6 extends Challenge[List[Long]] {

  override def part1(input: List[String]): List[Long] = input.map(solve(_, 4))

  // the first position where the four most recently received characters were all different.
  def solve(input: String, distinctChars: Int): Long = {
    @tailrec
    def solve(input: String, index: Int): Long = {
      index match {
        case x if x <= distinctChars => solve(input, index + 1)
        case x if x < input.length =>
          if (input.substring(index - distinctChars, index).distinct.length == distinctChars) index
          else solve(input, index + 1)
      }
    }
    solve(input, 0)
  }
  
  def part2(input: List[String]): List[Long] = input.map(solve(_, 14))

  def main(args: Array[String]): Unit = {
    this.printResult(2022, 6)
  }
}