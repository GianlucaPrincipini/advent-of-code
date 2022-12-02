package twentytwo

import utils.FileLoader

import scala.annotation.tailrec
import scala.util.{Failure, Success}

case class Elf(id: Long, caloriesSum: Long) {
  def >(o: Elf): Boolean = if (this.caloriesSum > o.caloriesSum) true else false
  def <(o: Elf): Boolean = if (this.caloriesSum < o.caloriesSum) true else false
}


object Day1 {

  def main(args: Array[String]): Unit = {
    FileLoader.tryLoadFile(2022, 1) match {
      case Success(rows) => println(s"Part1: ${part1(rows)}\tpart2: ${part2(rows)}")
      case Failure(exception) => exception.printStackTrace()
    }
  }

  /**
   * Recursive O(n) solution
   * @param input file rows
   * @return
   */
  def part1(input: List[String]): Elf = {
    @tailrec
    def recursiveComputeResult(current: Elf,
                               max: Elf,
                               list: List[String]): Elf = list match {
      case Nil => if (current > max) current else max
      case head :: tail => head.toIntOption match {
        case Some(calories) => recursiveComputeResult(Elf(current.id, current.caloriesSum + calories), max, tail)
        case None => recursiveComputeResult(Elf(current.id + 1, 0), if (current > max) current else max, tail)
      }
    }
    recursiveComputeResult(Elf(0, 0), Elf(0, 0), input)
  }

  /**
   * Recursive O(nlogn) solution
   * @param input file rows
   * @return sum of the best three elves calories sum
   */
  def part2(input: List[String]): Long = {
    @tailrec
    def recursiveComputeResult(current: Elf,
                               elfSum: List[Elf],
                               list: List[String]): List[Elf] = list match {
      case Nil => elfSum ::: List(current)
      case head :: tail => head.toIntOption match {
        case Some(calories) =>
          recursiveComputeResult(Elf(current.id, current.caloriesSum + calories), elfSum, tail)
        case None =>
          recursiveComputeResult(Elf(current.id + 1, 0), (elfSum ::: List(current)), tail)
      }
    }
    recursiveComputeResult(Elf(0, 0), List.empty, input)
      .map(elf => elf.copy(caloriesSum = -elf.caloriesSum))
      .sortWith((elf1: Elf, elf2: Elf) => elf1 < elf2)
      .take(3)
      .map(-_.caloriesSum)
      .sum
  }
}
