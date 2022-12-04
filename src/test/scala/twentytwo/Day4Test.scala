package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day4Test extends AnyFlatSpec {

  it should "Match part 1 example" in {
    assert(FileLoader.tryLoadFile(2022, 4).map(Day4.part1) == Success(2))
  }

  it should "Match part 2 example" in {
    assert(FileLoader.tryLoadFile(2022, 4).map(Day4.part2) == Success(4))
  }
}
