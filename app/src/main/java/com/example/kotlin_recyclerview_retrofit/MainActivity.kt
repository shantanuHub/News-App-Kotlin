package com.example.kotlin_recyclerview_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_recyclerview_retrofit.fragments.FragmentOne
import com.example.kotlin_recyclerview_retrofit.Extensions.replaceFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(FragmentOne() , R.id.frame_layout)
    }





}

