package twentytwo

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

case class Crate(char: Char)
object Crate {
  val stackPattern: Regex = "\\[([a-zA-Z])\\]".r
  def apply(str: String): Crate = str match {
    case stackPattern(char) => Crate(char(0))
    case _ => Crate(Char.MinValue)
  }
}
case class Movement(n: Int, from: Int, to: Int)
object Movement {
  val movePattern: Regex = "move ([0-9]+) from ([0-9]+) to ([0-9]+)".r
  def apply(str: String): Movement = str match {
    case movePattern(stack, from, to) => Movement(stack.toInt, from.toInt, to.toInt)
  }
}

object Day5 extends Challenge[String] {

  type Stack = mutable.Stack[Crate]
  case class Context(crates: List[Stack], movements: List[Movement]) {
    def executeMultipleMovements: Context = {
      @tailrec
      def executeInnerMovements(crates: List[Stack], movements: List[Movement]): Context = {
        movements match {
          case ::(head, next) =>
            executeInnerMovements(moveTogether(crates, head.from - 1, head.to - 1, head.n), next)
          case Nil => Context(crates, movements)
        }
      }
      executeInnerMovements(crates, movements)
    }
    
  def executeMovements: Context = {
      @tailrec
      def executeInnerMovements(crates: List[Stack], movements: List[Movement]): Context = {
        movements match {
          case ::(head, next) => 
            executeInnerMovements(move(crates, head.from - 1, head.to - 1, head.n), next)
          case Nil => Context(crates, movements)
        }
      }
      executeInnerMovements(crates, movements)
    }
    private def move(crates: List[Stack], src: Int, dst: Int, n: Int): List[Stack] = {
      for (i <- 0 until n) {crates(dst).push(crates(src).pop)}
      crates
    }
    private def moveTogether(crates: List[Stack], src: Int, dst: Int, n: Int): List[Stack] = {
      val tmp: ListBuffer[Crate] = ListBuffer.empty
      move(crates(src), tmp, n)
      move(tmp.reverse, crates(dst), n) 
      crates
    }
    private def move(src: Stack, dst: ListBuffer[Crate], n: Int): ListBuffer[Crate] = {
      for (i <- 0 until n) {
        dst.append(src.pop)
      }
      dst
    }
    private def move(src: ListBuffer[Crate], dst: Stack, n: Int): Stack = {
      src.foreach(crate => dst.push(crate))
      dst
    }

    def getTopMessage: String = crates.map(_.top.char).mkString
  }

  def defineStatus(lines: List[String]): Context = {
    val stacks: List[Stack] = List.fill((lines.head.length + 1) / 4)(mutable.Stack.empty)
      lines.foldRight(Context(stacks, List.empty[Movement]))((line: String, acc: Context) => {
        line match {
          case (line: String) if line.startsWith("move") => {
            Context(acc.crates, Movement(line) :: acc.movements)
          }
          case (line: String) if line.trim.toIntOption.isEmpty => {
            val crates = line.grouped(4).toList.map(_.trim).filter(_.toIntOption.isEmpty)
            crates.zipWithIndex.foreach(crate => { 
              if (crate._1.nonEmpty) stacks(crate._2).push(Crate(crate._1))
            })
            (Context(stacks, acc.movements))
          }
          case _ => acc
        }
      })
  }

  override def part1(input: List[String]): String = {
    defineStatus(input).executeMovements.getTopMessage
  }
  def part2(input: List[String]): String =
    defineStatus(input).executeMultipleMovements.getTopMessage

  def main(args: Array[String]): Unit = {
    this.printResult(2022, 5)
  }
}