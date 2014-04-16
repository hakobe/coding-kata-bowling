
class Game {
  private var currentRoll = 0
  private val rolls = Array.fill(21)(0)

  def roll(pins: Int) : Unit = {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  def score() : Int = {
    var result = 0
    var rollIndex = 0
    for (_ <- 1 to 10) {
      if (isStrike(rollIndex)) {
        result += 10 + strikeBonus(rollIndex)
        rollIndex += 1
      } else if (isSpare(rollIndex)) {
        result += 10 + spareBonus(rollIndex)
        rollIndex += 2
      } else {
        result += rolls(rollIndex) + rolls(rollIndex+1)
        rollIndex += 2
      }
    }
    result
  }

  private def isSpare(rollIndex: Int) : Boolean = rolls(rollIndex) + rolls(rollIndex+1) == 10
  private def isStrike(rollIndex: Int) : Boolean = rolls(rollIndex) == 10

  private def spareBonus(rollIndex: Int) : Int = rolls(rollIndex+2)
  private def strikeBonus(rollIndex: Int) : Int = rolls(rollIndex+1) + rolls(rollIndex+2)

}
