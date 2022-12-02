package utils

import scala.io.Source
import scala.util.{Try, Using}

object FileLoader {
  /**
   * Returns
   * @param fileName
   * @return
   */
  def tryLoadFile(year: Int, day: Int): Try[List[String]] =
    Using(Source.fromResource(s"$year/$day.txt")) {source => source.getLines().toList}

}
