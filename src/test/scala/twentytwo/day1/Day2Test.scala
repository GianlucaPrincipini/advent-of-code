package twentytwo.day1

import org.scalatest.flatspec.AnyFlatSpec
import twentytwo.Day2
import utils.FileLoader

import scala.util.Success

class Day2Test extends AnyFlatSpec {

  it should "Compute strategy" in {
    assert(FileLoader.tryLoadFile(2022, 2).map(Day2.part1) == Success(15))
  }

  it should "Compute strategy 2" in {
    assert(FileLoader.tryLoadFile(2022, 2).map(Day2.part2) == Success(12))
  }
}
