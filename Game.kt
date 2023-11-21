//Game for two players by guessing combination of letter stand for colour RBYGOW

import kotlin.random.Random

fun generateCode(): String {
    val colors = "RBYGOW"
    return (1..4).map { colors.random() }.joinToString("")
}

fun giveFeedback(code: String, guess: String): Pair<Int, Int> {
    val correctPosition = code.zip(guess).count { it.first == it.second }
    var correctColors = setOf('R', 'B', 'Y', 'G', 'O', 'W').sumBy {
        minOf(code.count { c -> c == it }, guess.count { c -> c == it })
    }
    correctColors -= correctPosition
    return Pair(correctPosition, correctColors)
}

fun playGame() {
    var code = generateCode()
    var attempts = 0

    println("Welcome to the Color Code Game!")

    while (true) {
        print("Enter your guess (4 letters): ")
        var guess = readLine()?.toUpperCase()

        if (guess == null || guess.length != 4 || !guess.all { it in "RBYGOW" }) {
            println("Invalid input. Please enter 4 letters using R, B, Y, G, O, W.")
            continue
        }

        attempts++
        val feedback = giveFeedback(code, guess)

        println("Feedback: ${feedback.first} letters correct in the right position, " +
                "${feedback.second} correct but in the wrong position.")

        if (feedback.first == 4) {
            println("Congratulations! You guessed the code in $attempts attempts.")
            break
        }
    }
}

fun main() {
    playGame()
}
