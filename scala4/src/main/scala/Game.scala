
class Game {
  val rolls = Array.fill(21)(0)
  var rollIndex = 0

  def roll(pins :Int) : Unit = {
    rolls(rollIndex) = pins
    rollIndex += 1
  }

  def score() : Int = {
    var i = 0
    var result = 0

    for (frame <- 1 to 10) {
      if (isStrike(i)) {
        result += 10 + strikeBonus(i)
        i += 1
      } else if (isSpare(i)) {
        result += 10 + spareBonus(i)
        i += 2
      } else {
        result += rolls(i) + rolls(i+1)
        i += 2
      }
    }

    result
  }

  private[this] def isStrike(rollIndex: Int) : Boolean =
    rolls(rollIndex) == 10
  private[this] def strikeBonus(rollIndex: Int) : Int =
    rolls(rollIndex+1) + rolls(rollIndex+2)
  private[this] def isSpare(rollIndex: Int) : Boolean =
    rolls(rollIndex) + rolls(rollIndex+1) == 10
  private[this] def spareBonus(rollIndex: Int) : Int =
    rolls(rollIndex+2)
}

