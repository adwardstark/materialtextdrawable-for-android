package com.adwardstark.materialtextdrawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adwardstark.materialtextdrawable.components.AvatarAdapter
import com.adwardstark.materialtextdrawable.databinding.ActivityMainBinding
import com.adwardstark.mtextdrawable.MaterialTextDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinder: ActivityMainBinding

    private val listOfNames: Array<String> = arrayOf("Jon", "Max", "Ben" ,
        "Ron", "Noah", "Becky", "Steven", "Zack", "Aron", "Ryan", "Julia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    private fun init() {
        MaterialTextDrawable.with(this)
            .text("Hello")
            .colorMode(MaterialTextDrawable.MaterialColorMode.DARK)
            .into(viewBinder.avatarIcon1)

        MaterialTextDrawable.with(this)
            .text("Goodbye")
            .colorMode(MaterialTextDrawable.MaterialColorMode.LIGHT)
            .shape(MaterialTextDrawable.MaterialShape.RECTANGLE)
            .into(viewBinder.avatarIcon2, ImageView.ScaleType.CENTER_INSIDE)

        viewBinder.avatarRecView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter =
                AvatarAdapter(
                    listOfNames
                )
        }
    }
}