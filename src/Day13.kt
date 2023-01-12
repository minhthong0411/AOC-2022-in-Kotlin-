import java.util.*

fun main() {
    fun parseList(str: String): Any{
        val stackOfItems: Stack<Any> = Stack()
        val lstString = str.split(Regex(",|(?=[]])|(?<=[\\[])")).map {
            if(it == "[" || it == "]")
                it.single()
            else
                Char(it.toInt())
        }
        lstString.forEach { char ->
            if(char == ']'){
                var tempLst = mutableListOf<Any>()
                //tempLst.addAll(lst)
                while(stackOfItems.peek() != '['){
                    tempLst.add(stackOfItems.pop())
                    //stackOfItems.pop()
                }
                //pop '['
                stackOfItems.pop()
                tempLst = tempLst.reversed().toMutableList()
                stackOfItems.add(tempLst)
                //tempLst.addAll(lst)
                //println("TEMP LIST: $tempLst")
            }else{
                if(char == '[')
                    stackOfItems.push(char)
                else
                    stackOfItems.push(char.code)
            }
            //println("CURRENT STACK: $stackOfItems")
        }
        return stackOfItems.pop()
    }
    var isStillTie = true
    fun compareList(lst1: List<Any>, lst2: List<Any>) : Boolean{
        println("CURRENTLY ON PAIR: $lst1 $lst2")
        for(i in lst1.indices){
            if(i < lst2.size) {
                println("   WHAT IS BEING COMPARED? ${lst1[i]} ${lst2[i]}")
                if (lst1[i] is List<*> && lst2[i] is List<*>) {
                    val cmp = compareList(lst1[i] as List<Any>, lst2[i] as List<Any>)
                    println("   COMPARE LIST AND LIST $cmp")
                    println("STILL TIE? $isStillTie")
                    if(isStillTie){
                        continue
                    }else{
                        return cmp
                    }
                } else if (lst1[i] is List<*> && lst2[i] is Int) {
                    val cmp = compareList(lst1[i] as List<Any>, listOf(lst2[i]))
                    println("   COMPARE LIST AND INT $cmp" )
                    println("STILL TIE? $isStillTie")
                    if(isStillTie){
                        continue
                    }else{
                        return cmp
                    }
                } else if (lst1[i] is Int && lst2[i] is List<*>) {
                    val cmp = compareList(listOf(lst1[i]), lst2[i] as List<Any>)
                    println("   COMPARE INT AND LIST $cmp")
                    println("STILL TIE? $isStillTie")
                    if(isStillTie){
                        continue
                    }else{
                        return cmp
                    }
                } else {
                    println("   COMPARE INT AND INT " + (lst1[i].toString().toInt() > lst2[i].toString().toInt()))
                    if (lst1[i].toString().toInt() > lst2[i].toString().toInt()) {
                        isStillTie = false
                        return false
                    }else if(lst1[i].toString().toInt() < lst2[i].toString().toInt()){
                        isStillTie = false
                        return true
                    }else{
                        println("   CONTINUE TO COMPARE INT AND INT ")
                        continue
                    }
                }
            }else{
                return false //lst2 ran out of items
            }
        }
        println("FINAL RESULT: ")
        return true
    }

    fun part1(input: List<String>): Int {
        var count = 0
        var pairNumber = 0
        var total = 0
        while(count < input.size){
            val lst1 = parseList(input[count])
            val lst2 = parseList(input[count + 1])
            count += 3
            pairNumber += 1
            println(lst1)
            println(lst2)
        //println(compareList(lst1 as List<Any>, lst2 as List<Any>))
            val isCompared = compareList(lst1 as List<Any>, lst2 as List<Any>)
            println(isCompared)
            if(isCompared){
                total += pairNumber
            }
            //println(lst1)
            //println(compareItem(lst1, lst2))
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var index = 0
        //println(input)
        return index
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}