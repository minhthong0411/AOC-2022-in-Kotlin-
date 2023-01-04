import java.util.SortedSet

fun main() {
    data class Monkey(
        var monkeyId: Int = 0,
        var startingItems: MutableList<ULong> = mutableListOf(),
        var operation: String = "",
        var divisibilityTest: ULong = 1UL,
        var throwToMonkeyNumberIfTrue: Int = 0,
        var throwToMonkeyNumberIfFalse: Int = 0,
        var numOfInspected: Int = 0
    )

    fun parseMonkey(monkey: Monkey, listOfMonkey: MutableList<Monkey>){
        monkey.startingItems.forEach { worryLevel ->
            //println("STARTING WORRY LEVEL: $worryLevel")
            //Parse opration
            monkey.numOfInspected += 1
            var newWorryLevel: ULong
            if(monkey.operation.contains("old")){
                if (monkey.operation.contains('*')) {
                    newWorryLevel = (worryLevel * worryLevel).toULong()
                }else{
                    newWorryLevel = (worryLevel + worryLevel).toULong()
                }
            }else {
                if (monkey.operation.contains('*')) {
                    newWorryLevel = worryLevel.toULong() * (monkey.operation.substringAfter(" ")).toULong()
                }else{
                    newWorryLevel = worryLevel.toULong() + (monkey.operation.substringAfter(" ")).toULong()
                }
            }
            //println("NEW WORRY LEVEL: $newWorryLevel")
            //Divided by three
            newWorryLevel /= 3UL
            //println("MONKEY GETS BORED. DIVIDED WORRY LEVEL BY 3: $newWorryLevel")
            //Test divisibility
            if(newWorryLevel % (monkey.divisibilityTest.toULong()) == 0UL){
                //println("WORRY LEVEL IS DIVISIBLE BY ${monkey.divisibilityTest}. THROW TO MONKEY ${monkey.throwToMonkeyNumberIfTrue}")
                listOfMonkey[monkey.throwToMonkeyNumberIfTrue].startingItems.add(newWorryLevel.toULong())
            }else{
                //println("WORRY LEVEL IS NOT DIVISIBLE BY ${monkey.divisibilityTest}. THROW TO MONKEY ${monkey.throwToMonkeyNumberIfFalse}")
                listOfMonkey[monkey.throwToMonkeyNumberIfFalse].startingItems.add(newWorryLevel.toULong())
            }
        }
        monkey.startingItems.clear()
    }
    fun part1(input: List<String>): ULong {
        val numberOfMonkey = input.size / 6
        //println("THERE ARE: $numberOfMonkey")
        val listOfMonkey = List(numberOfMonkey){Monkey()}.toMutableList()
        var count = 0
        while(count < input.size) {
            if(input[count].isEmpty()) {
                count += 1
                continue
            }
            val monkeyId = input[count].replace(":", "").substringAfter("Monkey ")
            val startItems = input[count + 1].substringAfter("Starting items: ")
            val operation = input[count + 2].substringAfter("Operation: new = old ")
            val test = input[count + 3].substringAfter("Test: divisible by ")
            val ifTrue = input[count + 4].substringAfter("If true: throw to monkey ")
            val ifFalse = input[count + 5].substringAfter("If false: throw to monkey ")
            //println("Monkey $monkeyId Starting Items: $startItems Operation: $operation Test: $test IfTrue: $ifTrue IfFalse: $ifFalse")
            val newMonkey = Monkey(
                monkeyId.toInt(),
                startItems.split(", ").map { str -> str.toULong() }.toMutableList(),
                operation,
                test.toULong(),
                ifTrue.toInt(),
                ifFalse.toInt()
                )
            listOfMonkey[monkeyId.toInt()] = newMonkey
            count += 6
        }

        //Run 20 rounds
        var roundNumber = 1
        while(roundNumber <= 20) {
            //println("Round $roundNumber:")
            for (i in listOfMonkey.indices) {
                parseMonkey(listOfMonkey[i], listOfMonkey)
            }
//            if(roundNumber == 1) {
//                listOfMonkey.forEach { monkey ->
//                    println(monkey.numOfInspected)
//                }
//            }
            roundNumber += 1
        }
        //Find two most inspected
        val sortedSet = sortedSetOf<Int>()
        listOfMonkey.forEach { monkey ->
            sortedSet.add(monkey.numOfInspected)
        }
        return (sortedSet.elementAt(sortedSet.size - 2) * sortedSet.elementAt(sortedSet.size - 1)).toULong()
    }

    fun parseMonkey1(monkey: Monkey, listOfMonkey: MutableList<Monkey>, divisor: ULong){
        monkey.startingItems.forEach { worryLevel ->
            //println("STARTING WORRY LEVEL: $worryLevel")
            //Parse opration
            monkey.numOfInspected += 1
            var newWorryLevel: ULong
            if(monkey.operation.contains("old")){
                if (monkey.operation.contains('*')) {
                    newWorryLevel = worryLevel * worryLevel
                }else{
                    newWorryLevel = worryLevel + worryLevel
                }
            }else {
                if (monkey.operation.contains('*')) {
                    newWorryLevel = worryLevel * (monkey.operation.substringAfter(" ")).toULong()
                }else{
                    newWorryLevel = worryLevel + (monkey.operation.substringAfter(" ")).toULong()
                }
            }
            //println("NEW WORRY LEVEL: $newWorryLevel")
            //Divided by three
            newWorryLevel %= divisor
            //println("MONKEY GETS BORED. DIVIDED WORRY LEVEL BY 3: $newWorryLevel")
            //Test divisibility
            if(newWorryLevel % (monkey.divisibilityTest) == 0UL){
                //println("WORRY LEVEL IS DIVISIBLE BY ${monkey.divisibilityTest}. THROW TO MONKEY ${monkey.throwToMonkeyNumberIfTrue}")
                listOfMonkey[monkey.throwToMonkeyNumberIfTrue].startingItems.add(newWorryLevel)
            }else{
                //println("WORRY LEVEL IS NOT DIVISIBLE BY ${monkey.divisibilityTest}. THROW TO MONKEY ${monkey.throwToMonkeyNumberIfFalse}")
                listOfMonkey[monkey.throwToMonkeyNumberIfFalse].startingItems.add(newWorryLevel)
            }
        }
        monkey.startingItems.clear()
    }
    fun part2(input: List<String>): ULong {
        val numberOfMonkey = input.size / 4
        //println("THERE ARE: $numberOfMonkey")
        val listOfMonkey = List(numberOfMonkey){Monkey()}.toMutableList()
        var count = 0
        while(count < input.size) {
            if(input[count].isEmpty()) {
                count += 1
                continue
            }
            val monkeyId = input[count].replace(":", "").substringAfter("Monkey ")
            val startItems = input[count + 1].substringAfter("Starting items: ")
            val operation = input[count + 2].substringAfter("Operation: new = old ")
            val test = input[count + 3].substringAfter("Test: divisible by ")
            val ifTrue = input[count + 4].substringAfter("If true: throw to monkey ")
            val ifFalse = input[count + 5].substringAfter("If false: throw to monkey ")
            //println("Monkey $monkeyId Starting Items: $startItems Operation: $operation Test: $test IfTrue: $ifTrue IfFalse: $ifFalse")
            val newMonkey = Monkey(
                monkeyId.toInt(),
                startItems.split(", ").map { str -> str.toULong() }.toMutableList(),
                operation,
                test.toULong(),
                ifTrue.toInt(),
                ifFalse.toInt()
            )
            listOfMonkey[monkeyId.toInt()] = newMonkey
            count += 6
        }

        //Run 20 rounds
        var roundNumber = 1
        var divisor = 1UL
        listOfMonkey.forEach { monkey ->
            divisor *= monkey.divisibilityTest
        }
        while(roundNumber <= 10000) {
            //println("Round $roundNumber:")
            for (i in listOfMonkey.indices) {
                parseMonkey1(listOfMonkey[i], listOfMonkey, divisor)
            }
            if(roundNumber == 2000) {
                listOfMonkey.forEach { monkey ->
                    println(monkey.numOfInspected)
                }
            }
            roundNumber += 1
        }
        //Find two most inspected
        val sortedSet = sortedSetOf<Int>()
        listOfMonkey.forEach { monkey ->
            sortedSet.add(monkey.numOfInspected)
        }
        return sortedSet.elementAt(sortedSet.size - 2).toULong() * sortedSet.elementAt(sortedSet.size - 1).toULong()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}