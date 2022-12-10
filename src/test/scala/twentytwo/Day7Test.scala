package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day7Test extends AnyFlatSpec {

  it should "Solve part 1" in {
    assert(FileLoader.tryLoadFile(2022, 7)
      .map(Day7.part1) == Success(95437))
  }

  it should "Solve part 2" in {
    assert(FileLoader.tryLoadFile(2022, 7)
      .map(Day7.part2) == Success(24933642))
  }
}
