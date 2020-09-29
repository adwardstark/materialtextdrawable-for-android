package com.adwardstark.materialtextdrawable.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adwardstark.materialtextdrawable.R

/**
 * Created by Aditya Awasthi on 28/09/20.
 * @author github.com/adwardstark
 */

class AvatarAdapter(private val listOfNames: Array<String>): RecyclerView.Adapter<AvatarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        return AvatarViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.layout_avatar_item, parent, false
                )
        )
    }

    override fun getItemCount(): Int = listOfNames.size

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.bind(listOfNames[position])
    }

}