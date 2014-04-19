import org.specs2.mutable._

class GameSpec extends Specification {
  "ボウリングゲーム" should {
    "すべてガターの時" in {
      val game = new Game(Seq.fill(20)(0))
      game.score() should_== 0
    }

    "すべて1ピンしか倒せなかった時" in {
      val game = new Game(Seq.fill(20)(1))
      game.score() should_== 20
    }

    "スペアを一度だけとれた時" in {
      val game = new Game(Seq(5, 5, 3) ++ Seq.fill(17)(0))
      game.score() should_== 16
    }

    "ストライクを一度だけとれた時" in {
      val game = new Game(Seq(10, 3, 4) ++ Seq.fill(16)(0))
      game.score() should_== 24
    }

    "10フレーム目でストライクをとれた時" in {
      val game = new Game(Seq.fill(18)(0) ++ Seq(10, 3, 4))
      game.score() should_== 17
    }

    "10フレーム目でスペアをとれた時" in {
      val game = new Game(Seq.fill(18)(0) ++ Seq(5, 5, 3))
      game.score() should_== 13
    }

    "10フレーム目でスペアもストライクも取れなかった時" in {
      val game = new Game(Seq.fill(18)(0) ++ Seq(3, 4))
      game.score() should_== 7
    }

    "パーフェクトゲームの時" in {
      val game = new Game(Seq.fill(12)(10))
      game.score() should_== 300
    }
  }
}
