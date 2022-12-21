import java.util.SortedSet

fun main() {
    fun part1(input: List<String>): Int {
        var totalScore = 0
        input.forEach { str ->
            totalScore += determineScore1(str)
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        input.forEach { str ->
            totalScore += determineScore2(str)
        }
        return totalScore
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun determineScore2(str: String): Int{
    when(str){
        "A X" -> return 3
        "A Y" -> return 4
        "A Z" -> return 8
        "B X" -> return 1
        "B Y" -> return 5
        "B Z" -> return 9
        "C X" -> return 2
        "C Y" -> return 6
        "C Z" -> return 7
    }
    return 0
}

fun determineScore1(str: String): Int{
    when(str){
        "A X" -> return 4
        "A Y" -> return 8
        "A Z" -> return 3
        "B X" -> return 1
        "B Y" -> return 5
        "B Z" -> return 9
        "C X" -> return 7
        "C Y" -> return 2
        "C Z" -> return 6
    }
    return 0
}