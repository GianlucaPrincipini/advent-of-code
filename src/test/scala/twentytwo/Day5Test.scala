package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day5Test extends AnyFlatSpec {

  it should "Solve part 1" in {
    assert(FileLoader.tryLoadFile(2022, 5)
      .map(Day5.part1) == Success("CMZ"))
  }

  it should "Solve part 2" in {
    assert(FileLoader.tryLoadFile(2022, 5).map(Day5.part2) == Success("MCD"))
  }
}
