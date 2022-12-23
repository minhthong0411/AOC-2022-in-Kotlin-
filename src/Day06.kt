import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        var index = 0
        val str = input[0]
        while (index < str.length){
            if(index + 4 <= str.lastIndex) {
                val fourCharStr = str.substring(index, index + 4)
                var isRepeated = false
                checkloop@ for(j in fourCharStr.indices){
                    val modifiedFourCharStr = fourCharStr.substring(0, j) + fourCharStr.substring(j + 1)
                    if(modifiedFourCharStr.contains(fourCharStr[j])){
                        isRepeated = true
                        break@checkloop
                    }
                }
                if(isRepeated)
                    index += 1
                else
                    return (index + 4)
            }else{
                break
            }
        }
        return index
    }

    fun part2(input: List<String>): Int {
        var index = 0
        //println(input)
        val str = input[0]
        while (index < str.length){
            if(index + 14 <= str.lastIndex) {
                val fourCharStr = str.substring(index, index + 14)
                //println(fourCharStr)
                var isRepeated = false
                checkloop@ for(j in fourCharStr.indices){
                    val modifiedFourCharStr = fourCharStr.substring(0, j) + fourCharStr.substring(j + 1)
                    if(modifiedFourCharStr.contains(fourCharStr[j])){
                        isRepeated = true
                        break@checkloop
                    }
                }
                if(isRepeated)
                    index += 1
                else
                    return (index + 14)
            }else{
                break
            }
        }
        return index
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}