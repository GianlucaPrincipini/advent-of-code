package twentytwo

import utils.FileLoader

import scala.util.{Failure, Success}

trait Challenge[ResultType] {
  def printResult(year: Int, day: Int): Unit = {
    FileLoader.tryLoadFile(year, day) match {
      case Failure(exception) => exception.printStackTrace()
      case Success(rows) => {
        println(s"Part1: ${part1(rows)}")
        println(s"Part2: ${part2(rows)}")
      }
    }
  }

  def part1(input: List[String]): ResultType
  def part2(input: List[String]): ResultType
}
