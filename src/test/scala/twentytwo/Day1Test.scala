package twentytwo

import org.scalatest.flatspec.AnyFlatSpec
import utils.FileLoader

import scala.util.Success

class Day1Test extends AnyFlatSpec {

  it should "compute best three elves" in {
    assert(FileLoader.tryLoadFile(2022, 1).map(Day1.part2) == Success(45000))
  }

}
