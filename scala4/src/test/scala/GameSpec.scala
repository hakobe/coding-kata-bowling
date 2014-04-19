import org.specs2.matcher.Scope
import org.specs2.mutable._

class GameSpec extends Specification {
  trait GameScope extends Scope {
    val game = new Game()

    def rollMany(pins :Int, times: Int) : Unit = {
      for (_ <- 1 to times) {
        game.roll(pins)
      }
    }

    def rollSpare() : Unit = {
      game.roll(5)
      game.roll(5)
    }

    def rollStrike() : Unit = {
      game.roll(10)
    }
  }

  "ボウリングゲーム" should {
    "すべてガターだった場合" in new GameScope {
      rollMany(0, 20)
      game.score() should_== 0
    }

    "すべて1ピンのみ倒せた場合" in new GameScope {
      rollMany(1, 20)
      game.score() should_== 20
    }

    "ストライクが一度だけとれた時" in new GameScope {
      rollStrike()
      game.roll(3)
      game.roll(4)

      rollMany(0, 16)

      game.score() should_== 24
    }

    "スペアが一度だけとれた時" in new GameScope {
      rollSpare()
      game.roll(3)

      rollMany(0, 17)

      game.score() should_== 16
    }

    "パーフェクトゲームの時" in new GameScope {
      rollMany(10, 12)
      game.score() should_== 300
    }
  }
}
