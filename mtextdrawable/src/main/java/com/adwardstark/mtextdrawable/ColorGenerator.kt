package com.adwardstark.mtextdrawable

import java.util.*

/**
 * Created by Aditya Awasthi on 28/09/20.
 * @author github.com/adwardstark
 */

internal class ColorGenerator(colorMode: Int = 700) {

    private val randomiser: Stack<Int> = Stack()
    private val colors: Stack<Int> = Stack()

    init {
        if (colorMode==700){
            randomiser.addAll(
                //A 700
                listOf(
                    -0xd32f2f, -0xC2185B, -0x7B1FA2, -0x512DA8,
                    -0x303F9F, -0x1976D2, -0x0288D1, -0x0097A7,
                    -0x00796B, -0x388E3C, -0x689F38, -0xAFB42B,
                    -0xFBC02D, -0xFFA000, -0xF57C00,  -0xE64A19,
                    -0x5D4037, -0x616161, -0x455A64
                )
            )
        }

        //A400
        if(colorMode==400){
            randomiser.addAll(
                listOf(
                    -0xef5350, -0xEC407A, -0xAB47BC, -0x7E57C2,
                    -0x5C6BC0, -0x42A5F5, -0x29B6F6, -0x26C6DA,
                    -0x26A69A, -0x66BB6A, -0x9CCC65, -0xD4E157,
                    -0xFFEE58, -0xFFCA28, -0xFFA726, -0xFF7043,
                    -0x8D6E63, -0xBDBDBD, -0x78909C
                )
            )
        }

        //A900
        if(colorMode==900){
            randomiser.addAll(
                listOf(
                    -0xb71c1c, -0x880E4F, -0x4A148C, -0x311B92,
                    -0x1A237E, -0x0D47A1, -0x01579B, -0x006064,
                    -0x004D40, -0x1B5E20, -0x33691E, -0x827717,
                    -0xF57F17, -0xFF6F00, -0xE65100, -0xBF360C,
                    -0x3E2723, -0x212121, -0x263238
                )
            )
        }

    }

    fun getRandomColor(): Int {
        if(colors.size == 0) {
            while (!randomiser.isEmpty()) colors.push(randomiser.pop())
            Collections.shuffle(colors)
        }
        val color: Int = colors.pop()
        randomiser.push(color)
        return color
    }

}