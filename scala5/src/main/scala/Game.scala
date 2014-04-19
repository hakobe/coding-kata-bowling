import scala.annotation.tailrec

class Game(rolls: Iterable[Int]) {

  @tailrec
  private[this] def _score(rs: List[Int], s: Int) : Int = {
    rs match {
      // 10フレーム目
      case first :: second :: third :: Nil if first == 10 || first + second == 10 =>
        s + first + second + third
      // スペア
      case first :: second :: next :: other if first + second == 10 =>
        _score(next :: other, s + 10 + next)
      // ストライク
      case first :: next :: secondNext :: other if first == 10 =>
        _score(next :: secondNext :: other, s + 10 + next + secondNext)
      // 通常フレーム
      case first :: second :: other =>
        _score(other, s + first + second)
      case Nil =>
        s
    }
  }

  def score() : Int =  _score(rolls.toList, 0)
}
