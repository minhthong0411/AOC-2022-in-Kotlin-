import java.util.SortedSet

fun main() {
    fun part1(input: List<String>): Int {
        var carriedMost = 0
        var elfTotal = 0
        input.forEach { str ->
            if(str.isEmpty()){
                if(elfTotal > carriedMost){
                    carriedMost = elfTotal
                }
                elfTotal = 0
            }else{
                elfTotal += str.toInt()
            }
        }
        return carriedMost
    }

    fun part2(input: List<String>): Int {
        val sortedSet: SortedSet<Int> = sortedSetOf()
        var elfTotal = 0
        input.forEach { str ->
            if(str.isEmpty()){
                sortedSet.add(elfTotal)
                elfTotal = 0
            }else{
                elfTotal += str.toInt()
            }
        }
        sortedSet.add(elfTotal)
        return sortedSet.reversed().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
