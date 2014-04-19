import scala.annotation.tailrec

class Game(rolls: Iterable[Int]) {

  @tailrec
  private[this] def _score(rs: List[Int], s: Int) : Int = {
    rs match {
      // 10フレーム目
      case first :: second :: third :: Nil if isStrike(first) || isSpare(first, second) =>
        s + first + second + third
      // スペア
      case first :: second :: next :: other if isSpare(first, second) =>
        _score(next :: other, s + 10 + next)
      // ストライク
      case first :: next :: secondNext :: other if isStrike(first) =>
        _score(next :: secondNext :: other, s + 10 + next + secondNext)
      // 通常フレーム
      case first :: second :: other =>
        _score(other, s + first + second)
      case Nil =>
        s
    }
  }

  private[this] def isStrike(pins: Int) : Boolean = pins == 10
  private[this] def isSpare(pinsFirst: Int, pinsSecond: Int) : Boolean = pinsFirst + pinsSecond == 10

  def score() : Int =  _score(rolls.toList, 0)
}
