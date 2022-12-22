fun main() {
    fun part1(input: List<String>): Int {
        var priority = 0
        input.forEach {str ->
            val halfString = str.substring(str.length / 2)
            val firstHalf = str.substring(0, str.length/2)
            outer@ for(ch in firstHalf) {
                if(halfString.contains(ch, false)){
                    priority += if(ch.isLowerCase()){
                        (ch.code - 'a'.code + 1)
                    }else {
                        (ch.code - 'A'.code + 27)
                    }
                    break@outer
                }
            }
        }
        return priority
    }

    fun part2(input: List<String>): Int {
        var priority = 0
        var counter = 0
        while(counter < input.size){
            outer@ for(ch in input[counter]) {
                if(input[counter + 1].contains(ch, false)){
                    if(input[counter + 2].contains(ch, false)){
                        priority += if(ch.isLowerCase()){
                            (ch.code - 'a'.code + 1)
                        }else {
                            (ch.code - 'A'.code + 27)
                        }
                        break@outer
                    }
                }
            }
            counter += 3
        }
        return priority
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}