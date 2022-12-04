package twentytwo

case class Assignments(assignment1: Set[Long], assignment2: Set[Long]) {
  val intersection = assignment1.intersect(assignment2)
  def fullyContains: Boolean = intersection.size == assignment1.size || intersection.size == assignment2.size
}
object Assignments {
  def apply(line: String): Assignments = {
    line.split(',') match {
      case Array(assignment1, assignment2) => {
        Assignments(computeRange(assignment1), computeRange(assignment2))
      }
    }
  }
  def computeRange(assignment: String): Set[Long] = {
    (assignment.split('-')(0).toLong to assignment.split('-')(1).toLong).toSet
  }
}

object Day4 extends Challenge[Long] {
  def main(args: Array[String]): Unit = {
    printResult(2022, 4)
  }

  def part1(input: List[String]): Long =
    input
      .map(Assignments(_))
      .count(_.fullyContains)


  def part2(input: List[String]): Long =
    input
      .map(Assignments(_))
      .count(_.intersection.nonEmpty)

}