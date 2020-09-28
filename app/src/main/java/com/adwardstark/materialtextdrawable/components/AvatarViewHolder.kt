package com.adwardstark.materialtextdrawable.components

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adwardstark.mtextdrawable.MaterialTextDrawable
import kotlinx.android.synthetic.main.layout_avatar_item.view.*

/**
 * Created by Aditya Awasthi on 28/09/20.
 * @author github.com/adwardstark
 */

class AvatarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    internal fun bind(username: String) {
        itemView.apply {
            avatarText.text = username

            val textDrawable = MaterialTextDrawable
                .with(context)
                .text(username)
                .textSize(140)
                .getDrawable()

            avatarIcon.setImageDrawable(textDrawable)
        }
    }
}