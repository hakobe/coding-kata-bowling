
class Game {
  private val rolls = Array.fill(21)(0)
  private var currentRole = 0

  def roll(pins: Int) : Unit = {
    rolls(currentRole) = pins
    currentRole += 1
  }
  def score() : Int = {
    var result = 0
    var i = 0

    for ( _ <- 1 to 10 ) {
      if (isStrike(i)) {
        result += 10 + rolls(i + 1) + rolls(i + 2)
        i += 1
      } else if (isSpare(i)) {
        result += 10 + rolls(i+2)
        i += 2
      } else {
        result += rolls(i) + rolls(i+1)
        i += 2
      }
    }

    result
  }

  private def isSpare(i: Int) = rolls(i) + rolls(i+1) == 10
  private def isStrike(i: Int) = rolls(i) == 10
}
