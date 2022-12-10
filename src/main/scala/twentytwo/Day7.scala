package twentytwo

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.matching.Regex

object Day7 extends Challenge[Long] {
  type Dir = String

  final val cdPattern: Regex = "\\$ cd (\\w+|/)".r
  final val filePattern: Regex = "(\\d+) .*".r
  
  override def part1(input: List[String]): Long = {
    solve(input)
  }

  // Path navigation is a stack where cd .. is a pop operation
  @tailrec
  def solve(input: List[Dir], path: mutable.Stack[Dir] = mutable.Stack.empty, allocationTable: mutable.Map[Dir, Long] = mutable.Map.empty): Long = {
    input match {
      case ::(head, next) => head match {
        case str if (str.trim == "$ cd ..") => 
          solve(next, {path.pop; path}, allocationTable)
        case cdPattern(dir) => 
          solve(next, {path.push(dir); path}, {allocationTable.put(path.mkString, 0); allocationTable})
          
        case filePattern(size) => {
          solve(next, path, updateAllocationTable(size.toLong, path, allocationTable))
        }
        case _ => solve(next, path, allocationTable)
      }
      case Nil => allocationTable.filter(_._2 < 100000).values.sum
    }
  }
  
  def updateAllocationTable(size: Long, path: mutable.Stack[Dir], allocationTable: mutable.Map[Dir, Long]): mutable.Map[Dir, Long] = {
    val currentDir: mutable.Stack[Dir] = mutable.Stack.empty
    path.foreach(p => {
      currentDir.push(p)
      allocationTable.update(
        currentDir.mkString,
        allocationTable(currentDir.mkString) + size)
    })
    allocationTable
  }

  override def part2(input: List[String]): Long = ???
}