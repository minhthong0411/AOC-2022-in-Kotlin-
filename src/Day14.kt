import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        val listOfRockLocations = mutableSetOf<Pair<Int, Int>>()
        val listOfSandLocations = mutableSetOf<Pair<Int, Int>>()
        var totalSandUnit = 0
        input.forEach { locs ->
            val lstLocs = locs.split(" -> ")
            for (i in 0 until lstLocs.lastIndex) {
                val coord1 = lstLocs[i].split(",")
                val coord2 = lstLocs[i + 1].split(",")
                val coord1X = coord1[0].toInt()
                val coord1Y = coord1[1].toInt()
                val coord2X = coord2[0].toInt()
                val coord2Y = coord2[1].toInt()

                if (coord2X == coord1X) {
                    if (coord2Y >= coord1Y) {
                        for (i in coord1Y..coord2Y) {
                            listOfRockLocations.add(Pair(coord2X, i))
                        }
                    } else {
                        for (i in coord2Y..coord1Y) {
                            listOfRockLocations.add(Pair(coord2X, i))
                        }
                    }
                } else if (coord2Y == coord1Y) {
                    if (coord2X >= coord1X) {
                        for (i in coord1X..coord2X) {
                            listOfRockLocations.add(Pair(i, coord2Y))
                        }
                    } else {
                        for (i in coord2X..coord1X) {
                            listOfRockLocations.add(Pair(i, coord2Y))
                        }
                    }
                }
            }
        }
        //println(listOfRockLocations.sortedWith(compareBy { it.second }))
        val deepestLevel = listOfRockLocations.sortedWith(compareBy { it.second }).last().second
        val firstRockLoc = listOfRockLocations.sortedWith(compareBy { it.first }).first().first
        val lastRockLoc = listOfRockLocations.sortedWith(compareBy { it.first }).last().first
        //println(deepestLevel)
        //println("$firstRockLoc $lastRockLoc")
        var count = 0
        var keepProducingSand = true
        while (keepProducingSand){
            //println("ROUND: #$count")
            var sandUnitLocation = Pair(500, 0)
            var comeToRest = false
            do {
                //Check falling straight down
                var newLocation = Pair(sandUnitLocation.first, sandUnitLocation.second + 1)
                if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation)) {
                    sandUnitLocation = newLocation
                } else {
                    newLocation = Pair(sandUnitLocation.first - 1, sandUnitLocation.second + 1)
                    //Check falling down left
                    if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation)) {
                        sandUnitLocation = newLocation
                    } else {
                        newLocation = Pair(sandUnitLocation.first + 1, sandUnitLocation.second + 1)
                        //Check falling down right
                        if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation)) {
                            sandUnitLocation = newLocation
                        } else {
                            comeToRest = true
                        }
                    }
                }
                // Check if sand is out of the range of rocks
                if(!(sandUnitLocation.second < deepestLevel && sandUnitLocation.first in firstRockLoc..lastRockLoc)){
                    comeToRest = true
                    keepProducingSand = false
                }
            } while (!comeToRest)
            if(keepProducingSand) {
                listOfSandLocations.add(sandUnitLocation)
                totalSandUnit += 1
                //println("LIST OF SANDS SO FAR: $listOfSandLocations")
            }
            count += 1
        }
        return totalSandUnit
    }

    fun part2(input: List<String>): Int {
        val listOfRockLocations = mutableSetOf<Pair<Int, Int>>()
        val listOfSandLocations = mutableSetOf<Pair<Int, Int>>()
        var totalSandUnit = 0
        input.forEach { locs ->
            val lstLocs = locs.split(" -> ")
            for (i in 0 until lstLocs.lastIndex) {
                val coord1 = lstLocs[i].split(",")
                val coord2 = lstLocs[i + 1].split(",")
                val coord1X = coord1[0].toInt()
                val coord1Y = coord1[1].toInt()
                val coord2X = coord2[0].toInt()
                val coord2Y = coord2[1].toInt()

                if (coord2X == coord1X) {
                    if (coord2Y >= coord1Y) {
                        for (i in coord1Y..coord2Y) {
                            listOfRockLocations.add(Pair(coord2X, i))
                        }
                    } else {
                        for (i in coord2Y..coord1Y) {
                            listOfRockLocations.add(Pair(coord2X, i))
                        }
                    }
                } else if (coord2Y == coord1Y) {
                    if (coord2X >= coord1X) {
                        for (i in coord1X..coord2X) {
                            listOfRockLocations.add(Pair(i, coord2Y))
                        }
                    } else {
                        for (i in coord2X..coord1X) {
                            listOfRockLocations.add(Pair(i, coord2Y))
                        }
                    }
                }
            }
        }
        //println(listOfRockLocations.sortedWith(compareBy { it.second }))
        val deepestLevel = listOfRockLocations.sortedWith(compareBy { it.second }).last().second
        val firstRockLoc = listOfRockLocations.sortedWith(compareBy { it.first }).first().first
        val lastRockLoc = listOfRockLocations.sortedWith(compareBy { it.first }).last().first
        val floor = deepestLevel + 2
        println(floor)
        //println("$firstRockLoc $lastRockLoc")
        var count = 0
        var keepProducingSand = true
        while (keepProducingSand){
            //println("ROUND: #$count")
            var sandUnitLocation = Pair(500, 0)
            var comeToRest = false
            do {
                //Check falling straight down
                var newLocation = Pair(sandUnitLocation.first, sandUnitLocation.second + 1)
                if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation) && newLocation.second < floor) {
                    sandUnitLocation = newLocation
                } else {
                    newLocation = Pair(sandUnitLocation.first - 1, sandUnitLocation.second + 1)
                    //Check falling down left
                    if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation) && newLocation.second < floor) {
                        sandUnitLocation = newLocation
                    } else {
                        newLocation = Pair(sandUnitLocation.first + 1, sandUnitLocation.second + 1)
                        //Check falling down right
                        if (!listOfRockLocations.contains(newLocation) && !listOfSandLocations.contains(newLocation) && newLocation.second < floor) {
                            sandUnitLocation = newLocation
                        } else {
                            comeToRest = true
                        }
                    }
                }
                // Check if sand blocks the sand producer
                if(sandUnitLocation == Pair(500,0)){
                    comeToRest = true
                    keepProducingSand = false
                    listOfSandLocations.add(sandUnitLocation)
                    totalSandUnit += 1
                    println("SAND PRODUCER IS BLOCKED")
                }
            } while (!comeToRest)
            if(keepProducingSand) {
                listOfSandLocations.add(sandUnitLocation)
                totalSandUnit += 1
                //println("LIST OF SANDS SO FAR: $listOfSandLocations")
            }
            count += 1
        }
        return totalSandUnit
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day14")
    part1(input).println()
    part2(input).println()
}