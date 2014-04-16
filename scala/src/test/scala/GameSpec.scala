import org.specs2.matcher.Scope
import org.specs2.mutable._

class GameSpec extends Specification {

  trait game extends Scope {
    val game = new Game()

    def rollMany(n: Integer, pins: Integer) : Unit =
      for ( i <- 1 to n) {
        game.roll(pins)
      }

    def rollStrike() : Unit =
      game.roll(10)

    def rollSpare() : Unit = {
      game.roll(5)
      game.roll(5)
    }
  }

  "ボウリングゲームは" should {
    "ガーターのみのゲームのスコアを計算できる" in new game {
      rollMany(20, 0)
      game.score should_== 0
    }

    "全フレームで1ピンしか倒せなかった時のスコアを計算できる" in new game {
      rollMany(20, 1)
      game.score should_== 20
    }

    "一度だけスペアをとったときスコアのスコアを計算できる" in new game {
      rollSpare()
      game.roll(3)
      rollMany(17, 0)
      game.score should_== 16
    }

    "一度だけストライクをとったときのスコアを計算できる" in new game {
      rollStrike()
      game.roll(3)
      game.roll(4)
      rollMany(16, 0)
      game.score should_== 24
    }

    "パーフェクトゲームのスコアを計算できる" in new game {
      rollMany(12, 10)
      game.score should_== 300
    }
  }
}
