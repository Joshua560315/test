package com.joshua.webpage.kotlin

/**
 * Created by linzl on 17-1-13.
 */
fun main(args: Array<String>) {
//    ZipUtil.zip("/home/bmk/sql","/home/bmk/sql.zip")
    var prev = random(13)
    var next = 0
    for (times in 1..13) {
        next = random(13)
        println("prev:$prev,next:$next")
        if (prev<next) println("high")
        else if(prev == next ) println("draw")
        else println("low")
        prev = next
    }
}

fun random(max:Int):Int {
    var protoRandom = Math.random()
    while (protoRandom<max){
        protoRandom = protoRandom.times(10)
    }
    return protoRandom.toInt().mod(max)+1
}