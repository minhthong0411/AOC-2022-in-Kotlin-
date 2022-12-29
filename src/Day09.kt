import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var setOfLocations = mutableSetOf(Pair(0, 0))
        var currentHeadLocation = Pair(0, 0)
        var currentTailLocation = Pair(0, 0)
        input.forEach { command ->
            val temp = command.split(' ')
            val dir = temp[0]
            val steps = temp[1].toInt()
            for(i in 0 until steps){
                when(dir){
                    "L" -> {
                        currentHeadLocation = currentHeadLocation.copy(currentHeadLocation.first - 1, currentHeadLocation.second)
                    }
                    "R" -> {
                        currentHeadLocation = currentHeadLocation.copy(currentHeadLocation.first + 1, currentHeadLocation.second)
                    }
                    "U" -> {
                        currentHeadLocation = currentHeadLocation.copy(currentHeadLocation.first, currentHeadLocation.second + 1)
                    }
                    "D" -> {
                        currentHeadLocation = currentHeadLocation.copy(currentHeadLocation.first, currentHeadLocation.second - 1)
                    }
                }
                //If T is not on the same row and col as H
                if(currentHeadLocation.first != currentTailLocation.first && currentHeadLocation.second != currentTailLocation.second){
                    //Do not move if it is within sqrt(2) of each other else move T diagonally (45 degree) a distance sqrt(2) so that either T is on the same row of column as H
                    if(kotlin.math.abs(currentTailLocation.first - currentHeadLocation.first) == 1 && kotlin.math.abs(
                            currentTailLocation.second - currentHeadLocation.second
                        ) == 1){
                        //DO NOT MOVE T's location
                    }else{
                        if(kotlin.math.abs(currentTailLocation.first - currentHeadLocation.first) == 1){
                            currentTailLocation = if(currentHeadLocation.second > currentTailLocation.second)
                                currentHeadLocation.copy(currentHeadLocation.first, currentTailLocation.second + 1)
                            else
                                currentHeadLocation.copy(currentHeadLocation.first, currentTailLocation.second - 1)
                        }else if(kotlin.math.abs(currentTailLocation.second - currentHeadLocation.second) == 1){
                            currentTailLocation = if(currentHeadLocation.first > currentTailLocation.first)
                                currentHeadLocation.copy(currentTailLocation.first + 1, currentHeadLocation.second)
                            else
                                currentHeadLocation.copy(currentTailLocation.first - 1, currentHeadLocation.second)
                        }
                    }
                }else{
                    if(currentHeadLocation.first == currentTailLocation.first && currentHeadLocation.second == currentTailLocation.second){
                        //DO NOT MOVE
                    }else if(currentHeadLocation.first == currentTailLocation.first){
                        if(kotlin.math.abs(currentHeadLocation.second - currentTailLocation.second) > 1) {
                            currentTailLocation = if (currentHeadLocation.second > currentTailLocation.second)
                                currentHeadLocation.copy(currentTailLocation.first, currentTailLocation.second + 1)
                            else
                                currentHeadLocation.copy(currentTailLocation.first, currentTailLocation.second - 1)
                        }

                    }else {
                        if(abs(currentHeadLocation.first - currentTailLocation.first) > 1) {
                            currentTailLocation = if (currentHeadLocation.first > currentTailLocation.first)
                                currentHeadLocation.copy(currentTailLocation.first + 1, currentTailLocation.second)
                            else
                                currentHeadLocation.copy(currentTailLocation.first - 1, currentTailLocation.second)
                        }
                    }
                }
                setOfLocations.add(currentTailLocation)
                //println("$command $currentHeadLocation $currentTailLocation")
            }
        }
        return setOfLocations.size
    }
    fun updateLocation(currentHeadLocation: Pair<Int, Int>, currentTailLocation: Pair<Int, Int>): Pair<Int, Int>{
        //If T is not on the same row and col as H
        var temp = currentTailLocation
        if(currentHeadLocation.first != currentTailLocation.first && currentHeadLocation.second != currentTailLocation.second){
            //Do not move if it is within sqrt(2) of each other else move T diagonally (45 degree) a distance sqrt(2) so that either T is on the same row of column as H
            if(kotlin.math.abs(currentTailLocation.first - currentHeadLocation.first) == 1 && kotlin.math.abs(
                    currentTailLocation.second - currentHeadLocation.second
                ) == 1){
                //DO NOT MOVE T's location
            }else{
                if(kotlin.math.abs(currentTailLocation.first - currentHeadLocation.first) == 1){
                    temp = if(currentHeadLocation.second > currentTailLocation.second)
                        currentHeadLocation.copy(currentHeadLocation.first, currentTailLocation.second + 1)
                    else
                        currentHeadLocation.copy(currentHeadLocation.first, currentTailLocation.second - 1)
                }else if(kotlin.math.abs(currentTailLocation.second - currentHeadLocation.second) == 1){
                    temp = if(currentHeadLocation.first > currentTailLocation.first)
                        currentHeadLocation.copy(currentTailLocation.first + 1, currentHeadLocation.second)
                    else
                        currentHeadLocation.copy(currentTailLocation.first - 1, currentHeadLocation.second)
                }else{
                    val delX = currentTailLocation.first - currentHeadLocation.first
                    val delY = currentTailLocation.second - currentHeadLocation.second
                    //1st quad
                    temp = if(delX > 0 && delY > 0){
                        currentHeadLocation.copy(currentHeadLocation.first + 1, currentHeadLocation.second + 1)
                    }else if(delX < 0 && delY > 0){ //2nd quad
                        currentHeadLocation.copy(currentHeadLocation.first - 1, currentHeadLocation.second + 1)
                    }else if(delX < 0 && delY < 0){
                        currentHeadLocation.copy(currentHeadLocation.first - 1, currentHeadLocation.second - 1)
                    }else{//quad 4
                        currentHeadLocation.copy(currentHeadLocation.first + 1, currentHeadLocation.second - 1)
                    }
                }
            }
        }else{
            if(currentHeadLocation.first == currentTailLocation.first && currentHeadLocation.second == currentTailLocation.second){
                //DO NOT MOVE
            }else if(currentHeadLocation.first == currentTailLocation.first){
                if(kotlin.math.abs(currentHeadLocation.second - currentTailLocation.second) > 1) {
                     temp = if (currentHeadLocation.second > currentTailLocation.second)
                        currentHeadLocation.copy(currentTailLocation.first, currentTailLocation.second + 1)
                    else
                        currentHeadLocation.copy(currentTailLocation.first, currentTailLocation.second - 1)
                }
            }else {
                if(abs(currentHeadLocation.first - currentTailLocation.first) > 1) {
                    temp = if (currentHeadLocation.first > currentTailLocation.first)
                        currentHeadLocation.copy(currentTailLocation.first + 1, currentTailLocation.second)
                    else
                        currentHeadLocation.copy(currentTailLocation.first - 1, currentTailLocation.second)
                }
            }
        }
        return temp
    }

    fun part2(input: List<String>): Int {
        val setOfLocations =  mutableSetOf(Pair(0, 0))
        val listOfLocations = MutableList(10) { Pair(0, 0)}
        input.forEach { command ->
            val temp = command.split(' ')
            val dir = temp[0]
            val steps = temp[1].toInt()
            for(i in 0 until steps){
                when(dir){
                    "L" -> {
                        listOfLocations[0] = Pair(listOfLocations[0].first - 1, listOfLocations[0].second)
                    }
                    "R" -> {
                        listOfLocations[0] = Pair(listOfLocations[0].first + 1, listOfLocations[0].second)
                    }
                    "U" -> {
                        listOfLocations[0] = Pair(listOfLocations[0].first, listOfLocations[0].second + 1)
                    }
                    "D" -> {
                        listOfLocations[0] = Pair(listOfLocations[0].first, listOfLocations[0].second - 1)
                    }
                }
                for (j in 0 until listOfLocations.lastIndex){
                    val newLocation = updateLocation(listOfLocations[j], listOfLocations[j + 1])
                    listOfLocations[j + 1] = newLocation
                    if(j + 1 == listOfLocations.lastIndex){
                        setOfLocations.add(newLocation)
                    }
                }
                //setOfLocations.add(currentTailLocation)
                //println("$command")
                //println(listOfLocations)
            }
        }
        return setOfLocations.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}