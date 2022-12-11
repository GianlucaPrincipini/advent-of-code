package twentytwo

import scala.annotation.tailrec

sealed trait Direction

case object Right extends Direction

case object Left extends Direction

case object Up extends Direction

case object Down extends Direction
object Direction {
  val pattern = "(R|L|D|U) (\\d+)".r
  def apply(str: String) = str match {
    case "R" => Right
    case "L" => Left
    case "D" => Down
    case "U" => Up
  }
  def parseDirectionList(str: String): List[Direction] = str match {
    case pattern(direction, number) => List.fill(number.toInt)(Direction(direction))
  }
}

case class Knot(x: Int, y: Int) {
  def move(direction: Direction): Knot = direction match {
    case Right => this.copy(x = this.x + 1)
    case Left => this.copy(x = this.x - 1)
    case Up => this.copy(y = this.y + 1)
    case Down => this.copy(y = this.y -1)
  }
}

case class Status(knots: List[Knot]) {
  
  def moveMore(movements: List[Movement]) = {
//    knots.scanLeft(Knot(0,0))()
  }
  
  def move(direction: Direction, head: Knot, tail: Knot): Status = direction match {
    case Right =>
      if (head.x - tail.x <= 0)
        Status(head.move(Right), tail)
      else
        Status(head.move(Right), head)

    case Left =>
      if (head.x - tail.x >= 0)
        Status(head.move(Left), tail)
      else
        Status(head.move(Left),head)

    case Up =>
      if (head.y - tail.y <= 0)
        Status(head.move(Up), tail)
      else
        Status(head.move(Up), head)


    case Down =>
      if (head.y - tail.y >= 0)
        Status(head.move(Down), tail)
      else
        Status(head.move(Down), head)
  }
}

object Status {
  def apply(n: Int): Status = {
    Status(List.fill(n)(Knot(0,0)))
  }
  
  def apply(head: Knot, tail: Knot): Status = {
    Status(List(head, tail))
  }
}

object Day9 extends Challenge[Long] {

  override def part1(input: List[String]): Long = 
    executeMovements(input.flatMap(Direction.parseDirectionList)).size
  
  @tailrec
  def executeMovements(movements: List[Direction],
                       status: Status = Status(2),
                       visitedPositions: Set[Knot] = Set.empty): Set[Knot] = {
    val newVisitedPositions: Set[Knot] = visitedPositions ++ Set(status.knots.last)
    movements match {
      case ::(head, next) => 
        executeMovements(
          next, 
          status.move(head, status.knots.head, status.knots.last), 
          newVisitedPositions)
      case Nil => newVisitedPositions
    }
  }

  override def part2(input: List[String]): Long = {
      executeMovements(input.flatMap(Direction.parseDirectionList)).size  }

  def main(args: Array[String]): Unit = {
    this.printResult(2022, 9)
  }
}