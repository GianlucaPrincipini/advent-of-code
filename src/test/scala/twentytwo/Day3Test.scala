package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day3Test extends AnyFlatSpec {

  it should "Sum priority" in {
    assert(FileLoader.tryLoadFile(2022, 3).map(Day3.part1) == Success(157))
  }

  it should "Get badge sum" in {
    assert(FileLoader.tryLoadFile(2022, 3).map(Day3.part2) == Success(70))
  }
}
