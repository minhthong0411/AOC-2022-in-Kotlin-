fun main() {
    fun part1(input: List<String>): Int {
        var totalPairs = 0
        input.forEach { str ->
            val ranges = str.split(',')
            val firstRange = ranges[0]
            val secondRange = ranges[1]
            val firstRangeLowerBound = firstRange.substring(0, firstRange.indexOf('-')).toInt()
            val firstRangeUpperBound = firstRange.substring(firstRange.indexOf('-') + 1).toInt()
            val secondRangeLowerBound = secondRange.substring(0, secondRange.indexOf('-')).toInt()
            val secondRangeUpperBound = secondRange.substring(secondRange.indexOf('-') + 1).toInt()
            if ((firstRangeLowerBound <= secondRangeLowerBound && firstRangeUpperBound >= secondRangeUpperBound) ||
                (secondRangeLowerBound <= firstRangeLowerBound && secondRangeUpperBound >= firstRangeUpperBound)){
                totalPairs += 1
            }
        }
        return totalPairs
    }

    fun part2(input: List<String>): Int {
        var totalOverlappingPairs = 0
        input.forEach { str ->
            val ranges = str.split(',')
            val firstRange = ranges[0]
            val secondRange = ranges[1]
            val firstRangeLowerBound = firstRange.substring(0, firstRange.indexOf('-')).toInt()
            val firstRangeUpperBound = firstRange.substring(firstRange.indexOf('-') + 1).toInt()
            val secondRangeLowerBound = secondRange.substring(0, secondRange.indexOf('-')).toInt()
            val secondRangeUpperBound = secondRange.substring(secondRange.indexOf('-') + 1).toInt()
            if (!(firstRangeLowerBound > secondRangeUpperBound || firstRangeUpperBound < secondRangeLowerBound)){
                totalOverlappingPairs += 1
            }
        }
        return totalOverlappingPairs
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}