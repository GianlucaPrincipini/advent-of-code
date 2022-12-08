package twentytwo

object Day6 extends Challenge[List[Long]] {

  override def part1(input: List[String]): List[Long] = input.map(solve)

  def solve(input: String): Long = 0
  
  def part2(input: List[String]): List[Long] = ???

  def main(args: Array[String]): Unit = {
    this.printResult(2022, 6)
  }
}