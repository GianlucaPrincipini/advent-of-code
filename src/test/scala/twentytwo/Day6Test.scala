package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day6Test extends AnyFlatSpec {

  it should "Solve part 1" in {
    assert(FileLoader.tryLoadFile(2022, 6)
      .map(Day6.part1) == Success(List(5, 6, 10, 11)))
  }

  it should "Solve part 2" in {
//    assert(FileLoader.tryLoadFile(2022, 6).map(Day6.part2) == Success("MCD"))
  }
}
