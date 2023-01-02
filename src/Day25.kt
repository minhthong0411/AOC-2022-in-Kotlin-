import java.util.SortedSet

fun main() {
    fun part1(input: List<String>): String {
        var total: ULong = 0UL
        input.forEach { numStr ->
            var num = 0UL
            numStr.forEach { char ->
                val digit = when (char) {
                    '1' -> 1
                    '2' -> 2
                    '0' -> 0
                    '-' -> -1
                    else -> -2
                }
                num = num * 5UL + digit.toULong()
            }
            total += num
        }
        //println("TOTAL $total")
        var str = ""
        while(total > 0UL){
            val rem = total % 5UL
            str += when(rem){
                1UL -> "1"
                2UL -> "2"
                0UL -> "0"
                4UL -> "-"
                else -> "="
            }
            if(rem == 3UL || rem == 4UL) total = total / 5UL + 1UL
            else total /= 5UL
        }
        return str.reversed()
    }

    fun part2(input: List<String>): Int {
        val sortedSet: SortedSet<Int> = sortedSetOf()
        return sortedSet.reversed().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day25")
    part1(input).println()
    part2(input).println()
}
