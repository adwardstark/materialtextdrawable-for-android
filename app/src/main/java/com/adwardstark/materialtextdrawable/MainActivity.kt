package com.adwardstark.materialtextdrawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val textDrawable1 = MaterialTextDrawable
            .with(this)
            .text("Hello")
            .colorMode(MaterialTextDrawable.MaterialColorMode.DARK)
            .getDrawable()

        viewBinder.avatarIcon1.setImageDrawable(textDrawable1)

        val textDrawable2 = MaterialTextDrawable
            .with(this)
            .text("Goodbye")
            .colorMode(MaterialTextDrawable.MaterialColorMode.LIGHT)
            .shape(MaterialTextDrawable.MaterialShape.RECTANGLE)
            .getDrawable()

        viewBinder.avatarIcon2.setImageDrawable(textDrawable2)

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