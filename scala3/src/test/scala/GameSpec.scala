import org.specs2.matcher.Scope
import org.specs2.mutable._

class GameSpec extends Specification {
  trait GameScope extends Scope {
    val game = new Game()
    def rollMany(n: Int, times: Int) = {
      for (_ <- 1 to times) {
        game.roll(n)
      }
    }

    def rollSpare() = {
      game.roll(5)
      game.roll(5)
    }

    def rollStrike() = {
      game.roll(10)
    }
  }

  "ボウリングゲーム" should {
    "すべてガーターであったときのスコアを計算できる" in new GameScope {
      rollMany(0, 20)
      game.score() should_== 0
    }

    "すべて1ピンだけ倒したときのスコアが計算できる" in new GameScope {
      rollMany(1, 20)
      game.score() should_== 20
    }

    "スペア一度だけあったときのスコアを計算できる" in new GameScope {
      rollSpare()
      game.roll(3)
      rollMany(0, 17)
      game.score() should_== 16
    }

    "ストライク一度だけあったときのスコアを計算できる" in new GameScope {
      rollStrike()
      game.roll(4)
      game.roll(3)
      rollMany(0, 16)
      game.score() should_== 24
    }

    "パーフェクトゲームのスコアが計算できる" in new GameScope {
      rollMany(10, 12)
      game.score() should_== 300
    }
  }
}
