package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day8Test extends AnyFlatSpec {

  it should "Solve part 1" in {
    assert(FileLoader.tryLoadFile(2022, 8)
      .map(Day8.part1) == Success(21))
  }

  it should "Solve part 2" in {
    assert(FileLoader.tryLoadFile(2022, 8)
      .map(Day8.part2) == Success(8))
  }
}