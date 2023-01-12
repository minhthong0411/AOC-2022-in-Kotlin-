import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import javax.print.attribute.standard.Destination
import kotlin.math.abs

fun main() {
    fun printMap(out: BufferedWriter, matrix: List<MutableList<Char>>) {
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                out.append(matrix[i][j] + " ")
            }
            out.append("\n")
        }
    }

    fun bfs(matrix: List<MutableList<Char>>, source: Pair<Int, Int>, destination: Pair<Int, Int>): Int {
        val map = List(matrix.size) { MutableList(matrix[0].size) { '.' } }
        val dir = listOf(Pair(0, 1), Pair(0, -1), Pair(-1, 0), Pair(1, 0))
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val visited = List(matrix.size) { MutableList(matrix[0].size) { false } }
        val parents = List(matrix.size) { MutableList(matrix[0].size) { Pair(-1, -1) } }
        var totalMoves = -1
        queue.add(source)
        visited[source.first][source.second] = true
        while (queue.isNotEmpty()) {
            val node = queue.remove()
            if (node.first == destination.first && node.second == destination.second) {
                //println("REACH THE DESTINATION")
                break
            }
            //Explore
            for (i in dir.indices) {
                val newNode = Pair(node.first + dir[i].first, node.second + dir[i].second)
                if (newNode.first < 0 || newNode.second < 0) continue
                if (newNode.first >= matrix.size || newNode.second >= matrix[0].size) continue
                if (visited[newNode.first][newNode.second]) continue
                if (matrix[newNode.first][newNode.second] - matrix[node.first][node.second] > 1) continue
                queue.add(newNode)
                visited[newNode.first][newNode.second] = true
                parents[newNode.first][newNode.second] = node

            }
//            println("CURRENT QUEUE $queue")
//            queue.forEach { pair ->
//                map[pair.first][pair.second] = matrix[pair.first][pair.second]
//            }
//            FileOutputStream("BFS.txt", true).bufferedWriter().use { out ->
//                printMap(out, map)
//            }
            //printMap(map)
        }
        //println(parents)
        var loc = destination
        while (loc.first != -1 && loc.second != -1) {
            totalMoves += 1
            loc = parents[loc.first][loc.second]
        }
        return totalMoves
    }

    fun searchForSource(matrix: List<MutableList<Char>>, ch: Char): Pair<Int, Int> {
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == ch)
                    return Pair(i, j)
            }
        }
        return Pair(-1, -1)
    }

    fun part1(input: List<String>): Int {
        val matrix = List(input.size) { MutableList(input[0].length) { ' ' } }
        for (i in input.indices) {
            for (j in input[i].indices)
                matrix[i][j] = input[i][j]
        }
        val sourceLocation = searchForSource(matrix, 'S')
        val destinationLocation = searchForSource(matrix, 'E')
        matrix[sourceLocation.first][sourceLocation.second] = 'a'
        matrix[destinationLocation.first][destinationLocation.second] = 'z'
        //println(destinationLocation)
        return bfs(matrix, sourceLocation, destinationLocation)
    }

    fun part2(input: List<String>): Int {
        val matrix = List(input.size) { MutableList(input[0].length) { ' ' } }
        for (i in input.indices) {
            for (j in input[i].indices)
                matrix[i][j] = input[i][j]
        }
        val sourceLocation = searchForSource(matrix, 'S')
        val destinationLocation = searchForSource(matrix, 'E')
        matrix[sourceLocation.first][sourceLocation.second] = 'a'
        matrix[destinationLocation.first][destinationLocation.second] = 'z'
        var fewestSteps = bfs(matrix, sourceLocation, destinationLocation)

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 'a') {
                    val steps = bfs(matrix, Pair(i, j), destinationLocation)
                    if (steps in 1 until fewestSteps) fewestSteps = steps
                }
            }
        }
        return fewestSteps
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}