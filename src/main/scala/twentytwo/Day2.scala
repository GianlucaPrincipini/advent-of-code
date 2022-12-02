package twentytwo

import utils.FileLoader

import scala.util.{Failure, Success}

sealed trait Hand {
  val value: Int
  def versus(opponent: Hand): Outcome
  def + (opponent: Hand): Long = value + versus(opponent).value
}
case object Rock extends Hand {
  override val value = 1
  override def versus(opponent: Hand): Outcome = opponent match {
    case Rock => Draw
    case Paper => Loss
    case Scissor => Win
  }
}
case object Paper extends Hand {
  override val value = 2
  override def versus(opponent: Hand): Outcome = opponent match {
    case Rock => Win
    case Paper => Draw
    case Scissor => Loss
  }
}
case object Scissor extends Hand {
  override val value = 3
  override def versus(opponent: Hand): Outcome = opponent match {
    case Rock => Loss
    case Paper => Win
    case Scissor => Draw
  }
}
object Hand {
  def apply(hand: String): Hand = hand match {
    case "A" | "X" => Rock
    case "B" | "Y" => Paper
    case "C" | "Z" => Scissor
  }
}

sealed trait Outcome {
  val value: Int
  def inferHand(opponent: Hand): Hand
}
case object Win extends Outcome {
  override val value: Int = 6
  override def inferHand(opponent: Hand): Hand = opponent match {
    case Rock => Paper
    case Paper => Scissor
    case Scissor => Rock
  }
}
case object Loss extends Outcome {
  override val value: Int = 0
  override def inferHand(opponent: Hand): Hand = opponent match {
    case Rock => Scissor
    case Paper => Rock
    case Scissor => Paper
  }
}
case object Draw extends Outcome {
  override def inferHand(opponent: Hand): Hand = opponent
  override val value: Int = 3
}
object Outcome {
  def apply(str: String): Outcome = str match {
    case "X" => Loss
    case "Y" => Draw
    case "Z" => Win
  }
}

object Day2 {
  type Round1 = (Hand, Hand)
  type Round2 = (Hand, Outcome)
  def main(args: Array[String]): Unit = {
    FileLoader.tryLoadFile(2022, 2) match {
      case Failure(exception) => exception.printStackTrace()
      case Success(rows) => println(s"Part1: ${part1(rows)}\tpart2: ${part2(rows)}")
    }
  }

  def part1(input: List[String]): Long = {
    input.map(toRound1)
      .map(round => (round._2 + round._1))
      .sum
  }

  def part2(input: List[String]): Long = {
    input.map(toRound2)
      .map(round => (round._2 inferHand round._1) + round._1)
      .sum
  }

  private def toRound1(input: String): Round1 = input.split(" ") match {
    case Array(opponent, you) => (Hand(opponent), Hand(you))
  }
  private def toRound2(input: String): Round2 = input.split(" ") match {
    case Array(opponent, outcome) => (Hand(opponent), Outcome(outcome))
  }

}