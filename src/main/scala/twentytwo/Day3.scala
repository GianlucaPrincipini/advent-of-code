package twentytwo

case class Rucksack(firstCompartment: Set[Item], secondCompartment: Set[Item]) {
  def computeSharedPriority: Int = (firstCompartment intersect secondCompartment).head.priority
  def getAll: Set[Item] = (firstCompartment ++ secondCompartment)
}
object Rucksack {
  def apply(string: String): Rucksack =
    Rucksack(
      string.substring(0, string.length/2).map(char => Item(char)).toSet,
      string.substring(string.length/2).map(char => Item(char)).toSet
    )
}
case class Group(rucksacks: Seq[Rucksack]) {
  def getBadge: Item =
    rucksacks
      .map(_.getAll)
      .reduce((a: Set[Item], b: Set[Item]) => a intersect b)
      .head
}
case class Item(value: Char, priority: Int)
object Item {
  private val priorities: Map[Char, Int] =
    (('a' to 'z') zip (1 to 26)).toMap ++ (('A' to 'Z') zip (27 to 52)).toMap

  def apply(char: Char): Item = Item(char, priorities(char))
}

object Day3 extends Challenge[Long] {
  def main(args: Array[String]): Unit = {
    printResult(2022, 3)
  }

  def part1(input: List[String]): Long =
    input
      .map(Rucksack(_).computeSharedPriority)
      .sum

  def part2(input: List[String]): Long =
    input
      .grouped(3)
      .map(group => Group(group.map(Rucksack(_))))
      .map(_.getBadge.priority)
      .sum

}