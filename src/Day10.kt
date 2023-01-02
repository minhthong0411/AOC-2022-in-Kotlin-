fun main() {
    fun part1(input: List<String>): Int {
        var currentCycle = 0
        var xVal = 1
        var signalStrength = 0
        input.forEach { command ->
           //println("READ: $command")
            if(command.contains("noop")){
                currentCycle += 1
                if(currentCycle % 40 == 20){
                    signalStrength += (xVal * currentCycle)
                }
                //println("During Noop cyle curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
            }else{
                currentCycle += 1
                if(currentCycle % 40 == 20){
                    signalStrength += (xVal * currentCycle)
                }
                //println("During ADDX first Cylce curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
                currentCycle += 1
                if(currentCycle % 40 == 20){
                    signalStrength += (xVal * currentCycle)
                }
                xVal += command.split(' ')[1].toInt()
                //println("During ADDX sceond Cylce curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
            }
        }
        return signalStrength
    }


    fun part2(input: List<String>): String {
        var currentCycle = 0
        var xVal = 1
        var crt = ""
        var longString = ""
        input.forEach { command ->
            //println("READ: $command")
            if(command.contains("noop")){
                currentCycle += 1
                if(crt.length in xVal - 1..xVal + 1) crt += "#" else crt += "."
                if(crt.length % 40 == 0) {
                    longString += crt
                    longString += "\n"
                    crt = ""
                }
                //println("During Noop cyle curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
                //println(longString)
            }else{
                currentCycle += 1
                if(crt.length in xVal - 1..xVal + 1) crt += "#" else crt += "."
                if(crt.length % 40 == 0) {
                    longString += crt
                    longString += "\n"
                    crt = ""
                }

                //println("During ADDX first Cylce curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
                //println(longString)
                currentCycle += 1
                if(crt.length in xVal - 1..xVal + 1) crt += "#" else crt += "."
                if(crt.length % 40 == 0) {
                    longString += crt
                    longString += "\n"
                    crt = ""
                }
                xVal += command.split(' ')[1].toInt()
                //println("During ADDX sceond Cylce curr X: $xVal currCycle: $currentCycle signal: $signalStrength")
                //println(longString)
            }
        }
        //println(longString)
        return longString
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}