import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        var totalVisible = 0
        for (row in 0..input.lastIndex){
            for(col in 0..input[row].lastIndex){
                val tree = input[row][col]
                //check edge
                if(row == 0 || row == input.lastIndex || col == 0 || col == input[row].lastIndex) totalVisible++
                else{
                    //println("TREE $row $col ${input[row][col]}")
                    var isVisibleLeft = true
                    var isVisibleRight = true
                    var isVisibleTop = true
                    var isVisibleBottom = true
                    //check top row
                    for(it in 0 until row){
                        if(tree <= input[it][col]){
                            isVisibleTop = false
                            break
                        }
                    }
                    //check bottom row
                    for(ib in row + 1 .. input.lastIndex){
                        if(tree <= input[ib][col]){
                            isVisibleBottom = false
                            break
                        }
                    }
                    //check left column
                    for(jl in 0 until col){
                        if(tree <= input[row][jl]){
                            isVisibleLeft = false
                            break
                        }
                    }
                    //check right column
                    for(jr in col + 1 .. input[row].lastIndex){
                        if(tree <= input[row][jr]){
                            isVisibleRight = false
                            break
                        }
                    }
                    //println("IS VISIBLE? LEFT: $isVisibleLeft RIGHT: $isVisibleRight")
                    if(isVisibleLeft || isVisibleRight || isVisibleTop || isVisibleBottom) totalVisible++
                }
            }
        }
        return totalVisible
    }

    fun part2(input: List<String>): Int {
        var bestScenicScore = 0
        for (row in 0..input.lastIndex){
            for(col in 0..input[row].lastIndex){
                val tree = input[row][col]
                var visibleTreesLeft = 0
                var visibleTreesRight = 0
                var visibleTreesTop = 0
                var visibleTreesBottom = 0
                //println("TREE $row $col ${input[row][col]}")
                //check top row
                for(it in row - 1 downTo 0){
                    if(tree <= input[it][col]){
                        visibleTreesTop++
                        break
                    }
                    visibleTreesTop++
                }
                //check bottom row
                for(ib in row + 1 .. input.lastIndex){
                    if(tree <= input[ib][col]){
                        visibleTreesBottom++
                        break
                    }
                    visibleTreesBottom++
                }
                //check left column
                for(jl in col - 1 downTo 0){
                    if(tree <= input[row][jl]){
                        visibleTreesLeft++
                        break
                    }
                    visibleTreesLeft++
                }
                //check right column
                for(jr in col + 1 .. input[row].lastIndex){
                    if(tree <= input[row][jr]){
                        visibleTreesRight++
                        break
                    }
                    visibleTreesRight++
                }
                val scenicScore = visibleTreesLeft * visibleTreesRight * visibleTreesTop * visibleTreesBottom
                //println("TOP: $visibleTreesTop BOT: $visibleTreesBottom LEFT: $visibleTreesLeft RIGHT: $visibleTreesRight SCORE: $scenicScore")
                if(scenicScore > bestScenicScore) bestScenicScore = scenicScore
            }
        }
        return bestScenicScore
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}