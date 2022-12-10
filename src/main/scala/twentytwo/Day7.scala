package twentytwo

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.matching.Regex

object Day7 extends Challenge[Long] {
  type Dir = String

  final val cdPattern: Regex = "\\$ cd (\\w+|/)".r
  final val filePattern: Regex = "(\\d+) .*".r
  
  override def part1(input: List[String]): Long = {
    solve(input, (allocationTable: mutable.Map[Dir, Long]) => allocationTable.filter(_._2 < 100000).values.sum)
  }

  // Path navigation is a stack where cd .. is a pop operation
  @tailrec
  def solve(input: List[Dir], logic: (mutable.Map[Dir, Long]) => Long, path: mutable.Stack[Dir] = mutable.Stack.empty, allocationTable: mutable.Map[Dir, Long] = mutable.Map.empty): Long = {
    input match {
      case ::(head, next) => head match {
        case str if (str.trim == "$ cd ..") => 
          solve(next, logic, {path.pop; path}, allocationTable)
        case cdPattern(dir) => {
          path.push(dir)
          allocationTable.put(path.mkString, 0)
          solve(next, logic, path, allocationTable)
        }
        case filePattern(size) => {
          solve(next, logic, path, updateAllocationTable(size.toLong, path, allocationTable))
        }
        case _ => solve(next, logic, path, allocationTable)
      }
      case Nil => logic(allocationTable)
    }
  }
  
  def updateAllocationTable(size: Long, path: mutable.Stack[Dir], allocationTable: mutable.Map[Dir, Long]): mutable.Map[Dir, Long] = {
    val currentDir: mutable.Stack[Dir] = mutable.Stack.empty
    path.reverse.foreach(dir => {
      currentDir.push(dir)
      allocationTable.update(
        currentDir.mkString,
        allocationTable(currentDir.mkString) + size)
    })
    allocationTable
  }

  override def part2(input: List[String]): Long =
    solve(input, (allocationTable: mutable.Map[Dir, Long]) => {
      val freeSpace: Long = 70000000 - allocationTable("/")
      allocationTable.values.filter(v => freeSpace + v >= 30000000).toList.sortWith(_ < _).head
    })


  def main(args: Array[String]): Unit = {
    this.printResult(2022, 7)
  }
}