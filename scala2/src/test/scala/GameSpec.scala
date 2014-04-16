import org.specs2.matcher.Scope
import org.specs2.mutable._

class GameSpec extends Specification {

  trait GameScope extends Scope {
    val game = new Game()

    def rollMany(n: Int, times: Int) : Unit = {
      for ( i <- 1 to times) {
        game.roll(n)
      }
    }

    def rollSpare() : Unit = {
      game.roll(5)
      game.roll(5)
    }

    def rollStrike() : Unit = game.roll(10)
  }

  "ボウリングゲーム" should {
    "すべてガーターのときのスコアを計算できる" in new GameScope {
      rollMany(0, 20)
      game.score() should_== 0
    }

    "全フレームで1ピンだけ倒した時のスコアが計算できる" in new GameScope {
      rollMany(1, 20)
      game.score() should_== 20
    }

    "一度だけスペアをとった時のスコアが計算できる" in new GameScope {
      rollSpare()
      game.roll(3)
      rollMany(0, 17)
      game.score() should_== 16
    }

    "一度だけストライクをとった時のスコアが計算できる" in new GameScope {
      rollStrike()
      game.roll(3)
      game.roll(4)
      rollMany(0, 16)
      game.score() should_== 24
    }

    "パーフェクトゲームのスコアが計算できる" in new GameScope {
      rollMany(10, 12)
      game.score() should_== 300
    }
  }
}
