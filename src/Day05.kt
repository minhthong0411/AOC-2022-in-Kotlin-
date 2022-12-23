import java.util.*

fun main() {
    fun part1(input: List<String>): String {
        val indexOfEmptyString = input.indexOf("")
        val strOfStackNumber = input[indexOfEmptyString - 1]
        val numOfStacks = strOfStackNumber[strOfStackNumber.lastIndex].digitToInt()
        val listOfStacks = List<Stack<String>>(numOfStacks){ Stack() }
        for(i in indexOfEmptyString - 2 downTo 0){
            val stackItems = input[i]
            var stackItemsIndex = 0
            var counter = 0
            while(stackItemsIndex < stackItems.length){
                val item = stackItems.substring(stackItemsIndex, stackItemsIndex + 3)
                if(item != "   "){
                    listOfStacks[counter].push(item)
                }
                counter++
                stackItemsIndex += 4
            }
        }
        for(i in indexOfEmptyString + 1..input.lastIndex){
            val tempStr = input[i].split(' ')
            val numOfItemsToMove = tempStr[1].toInt()
            val sourceStackNumber = tempStr[3].toInt() - 1
            val destinationStackNumber = tempStr[5].toInt() - 1
            for(j in 0 until numOfItemsToMove){
                if(listOfStacks[sourceStackNumber].isNotEmpty()) {
                    listOfStacks[destinationStackNumber].push(listOfStacks[sourceStackNumber].pop())
                }
            }
        }
        var word = ""
        listOfStacks.forEach { stack ->
            word += stack.peek()[1]
        }
        return word
    }

    fun part2(input: List<String>): String {
        val indexOfEmptyString = input.indexOf("")
        val strOfStackNumber = input[indexOfEmptyString - 1]
        val numOfStacks = strOfStackNumber[strOfStackNumber.lastIndex].digitToInt()
        val listOfStacks = List<Stack<String>>(numOfStacks){ Stack() }
        for(i in indexOfEmptyString - 2 downTo 0){
            val stackItems = input[i]
            var stackItemsIndex = 0
            var counter = 0
            while(stackItemsIndex < stackItems.length){
                val item = stackItems.substring(stackItemsIndex, stackItemsIndex + 3)
                if(item != "   "){
                    listOfStacks[counter].push(item)
                }
                counter++
                stackItemsIndex += 4
            }
        }
        for(i in indexOfEmptyString + 1..input.lastIndex){
            val tempStr = input[i].split(' ')
            val numOfItemsToMove = tempStr[1].toInt()
            val sourceStackNumber = tempStr[3].toInt() - 1
            val destinationStackNumber = tempStr[5].toInt() - 1
            val tempStack = Stack<String>()
            if(listOfStacks[sourceStackNumber].isNotEmpty()) {
                for (j in 0 until numOfItemsToMove) {
                    tempStack.push(listOfStacks[sourceStackNumber].pop())
                }
                for (j in 0 until numOfItemsToMove) {
                    listOfStacks[destinationStackNumber].push(tempStack.pop())
                }
            }
        }
        var word = ""
        listOfStacks.forEach { stack ->
            word += stack.peek()[1]
        }
        return word
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}