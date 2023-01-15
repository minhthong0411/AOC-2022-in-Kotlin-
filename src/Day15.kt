import java.io.BufferedWriter
import java.io.FileOutputStream
import java.util.*
import kotlin.math.abs

fun main() {
    fun vicinity(sensorLocations: MutableList<Pair<Int,Int>>, beaconLocations: MutableList<Pair<Int,Int>>, level: Int): Int{
        val vicinityArea = mutableSetOf<Pair<Int,Int>>()
        for(i in sensorLocations.indices) {
            val sensorLoc = sensorLocations[i]
            val beaconLoc = beaconLocations[i]
            val delX = abs(sensorLoc.first - beaconLoc.first)
            val delY = abs(sensorLoc.second - beaconLoc.second)
            val manhattanDistance = delX + delY
            if (level in sensorLoc.second - manhattanDistance..sensorLoc.second + manhattanDistance) {
                //println(manhattanDistance - abs(level - sensorLoc.second))
                for (col in 0..abs(manhattanDistance - abs(level - sensorLoc.second))) {
                    val pairs = listOf(
                        Pair(sensorLoc.first - col, level),
                        Pair(sensorLoc.first + col, level),
                    )
                    pairs.forEach { pair ->
                        if (!sensorLocations.contains(pair) && !beaconLocations.contains(pair)) {
                            vicinityArea.add(pair)
                        }
                    }
                }
            }
        }
        return vicinityArea.size
    }

    fun part1(input: List<String>): Int {
        val sensorLocations = mutableListOf<Pair<Int,Int>>()
        val beaconLocations = mutableListOf<Pair<Int,Int>>()
        input.forEach { signalStr ->
            val signal = signalStr.split(": ")
            val sensorStr = signal[0]
            val beaconStr = signal[1]
            val sensorLoc = sensorStr.substringAfter("Sensor at ").split(", ")
            val beaconLoc = beaconStr.substringAfter("closest beacon is at ").split(", ")
            sensorLocations.add(Pair(sensorLoc[0].substringAfter("x=").toInt(), sensorLoc[1].substringAfter("y=").toInt()))
            beaconLocations.add(Pair(beaconLoc[0].substringAfter("x=").toInt(), beaconLoc[1].substringAfter("y=").toInt()))
        }
        return vicinity(sensorLocations, beaconLocations, 2000000)
    }
    val bound = 4000000
    fun vicinity(sensorLocations: MutableList<Pair<Int,Int>>, beaconLocations: MutableList<Pair<Int,Int>>, level: Int, range: IntRange): Int{
        val listOfRanges = mutableListOf<IntRange>()
        val listOfPossibleLocations = mutableSetOf<Int>()
        for(i in sensorLocations.indices) {
            val sensorLoc = sensorLocations[i]
            val beaconLoc = beaconLocations[i]
            val delX = abs(sensorLoc.first - beaconLoc.first)
            val delY = abs(sensorLoc.second - beaconLoc.second)
            val manhattanDistance = delX + delY
            //println("LOCATION: $sensorLoc $beaconLoc")
            if (level in sensorLoc.second - manhattanDistance..sensorLoc.second + manhattanDistance) {
                val col = abs(manhattanDistance - abs(level - sensorLoc.second))
                var currRange = IntRange(
                    if(sensorLoc.first - col < 0) 0 else sensorLoc.first - col,
                    if(sensorLoc.first + col > bound) bound else sensorLoc.first + col
                )
                listOfRanges.add(currRange)
                listOfPossibleLocations.add(currRange.first - 1)
                listOfPossibleLocations.add(currRange.last + 1)
                // prevRange = [a,b] currRange = [c,d]
            }
        }
        for (i in listOfPossibleLocations.indices){
            var isInRange = false
            for(j in listOfRanges.indices){
                if(listOfPossibleLocations.elementAt(i) in listOfRanges[j] && listOfPossibleLocations.elementAt(i) in range){
                    isInRange = true
                    break
                }
            }
            if(!isInRange && listOfPossibleLocations.elementAt(i) in range){
                //println("FOUND IT ${listOfPossibleLocations.elementAt(i)}")
                return listOfPossibleLocations.elementAt(i)
            }
        }
//        println(listOfRanges)
//        println(listOfPossibleLocations)
        return -1
    }

    fun part2(input: List<String>): ULong {
        val sensorLocations = mutableListOf<Pair<Int,Int>>()
        val beaconLocations = mutableListOf<Pair<Int,Int>>()
        input.forEach { signalStr ->
            val signal = signalStr.split(": ")
            val sensorStr = signal[0]
            val beaconStr = signal[1]
            val sensorLoc = sensorStr.substringAfter("Sensor at ").split(", ")
            val beaconLoc = beaconStr.substringAfter("closest beacon is at ").split(", ")
            sensorLocations.add(Pair(sensorLoc[0].substringAfter("x=").toInt(), sensorLoc[1].substringAfter("y=").toInt()))
            beaconLocations.add(Pair(beaconLoc[0].substringAfter("x=").toInt(), beaconLoc[1].substringAfter("y=").toInt()))
        }
        //Check if level is in the sensor or beacon locations
        val endBound = 4000000
        val startBound = 0
        val range = IntRange(0, 4000000)
        for(level in startBound..endBound) {
            val v = vicinity(sensorLocations, beaconLocations, level, range)
            if(v != -1){
//                FileOutputStream("level.txt", true).bufferedWriter().use { out ->
//                    out.append("FOUND IT at LEVEL: $level POS: $v\n")
//                }
                //println("FOUND IT at LEVEL: $level")
                return 4000000UL * v.toULong() + level.toULong()
            }else{
//                FileOutputStream("level.txt", true).bufferedWriter().use { out ->
//                    out.append("AT LEVEL $level SIZE: $v\n")
//                }
//                println("AT LEVEL $level SIZE: $v")
                continue
            }
        }
        println("DONE FINDING IT")
        return 0UL
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}