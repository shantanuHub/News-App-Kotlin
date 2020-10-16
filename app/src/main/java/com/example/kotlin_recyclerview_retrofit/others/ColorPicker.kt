package com.example.kotlin_recyclerview_retrofit.others

object ColorPicker{

    var colors = arrayOf("#B83227" , "#2475B0" , "#6A89CC" , "#6AB04A" , "#10A881" , "#EEC213" , "#F9DDA4" , "#8395A7" ,"#8395A7" , "#8395A7")

    var number = 1
    fun getColor() :String
    {

        return colors[number++ % colors.size]
    }
}