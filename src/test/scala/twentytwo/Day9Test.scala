package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day9Test extends AnyFlatSpec {

  it should "Solve part 1" in {
    assert(FileLoader.tryLoadFile(2022, 9)
      .map(Day9.part1) == Success(13))
  }

  it should "Solve part 2" in {
    assert(FileLoader.tryLoadFile(2022, 9)
      .map(Day9.part2) == Success(8))
  }
}