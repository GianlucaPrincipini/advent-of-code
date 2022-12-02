package utils

import scala.io.Source
import scala.util.{Try, Using}

object FileLoader {
  /**
   * Returns
   * @param fileName
   * @return
   */
  def tryLoadFile(fileName: String): Try[List[String]] =
    Using(Source.fromResource(fileName)) {source => source.getLines().toList}

}
