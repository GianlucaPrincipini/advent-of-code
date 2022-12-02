package day1
import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class MainTest extends AnyFlatSpec {

  it should "compute best three elves" in {
    assert(FileLoader.tryLoadFile("day1_example.txt").map(Main.part2(_)) == Success(45000))
  }

}
