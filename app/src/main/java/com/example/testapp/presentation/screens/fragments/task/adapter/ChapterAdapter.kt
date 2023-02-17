package com.example.testapp.presentation.screens.fragments.task.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.domain.extentions.setImage
import com.example.testapp.presentation.models.ChapterItem
import com.google.android.material.card.MaterialCardView

class ChapterAdapter: ListAdapter<ChapterItem, ChapterVH>(ChapterItemDU()) {

    private var list: List<ChapterItem> = ArrayList()
    private var listener: ChapterVH.ChapterItemListener? = null

    fun setData(list: List<ChapterItem>) {
        this.list = list
        submitList(this.list)
    }

    fun setListener(listener: ChapterVH.ChapterItemListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterVH {
        return ChapterVH.create(parent)
    }

    override fun onBindViewHolder(holder: ChapterVH, position: Int) {
        return holder.bind(list[position], listener, position == list.lastIndex)
    }
}

class ChapterVH(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ChapterItem, listener: ChapterItemListener?, isLast: Boolean) {
        val icon = view.findViewById<ImageView>(R.id.chapter_icon)
        val text = view.findViewById<TextView>(R.id.chapter_name)
        val selector = view.findViewById<View>(R.id.chapter_selector)

        if(isLast) selector.visibility = GONE

        view.rootView.setOnClickListener {
            listener?.onColorClick()
        }

        text.text = item.name
        //icon.setImage(item.image)
    }

    interface ChapterItemListener{
        fun onColorClick()
    }

    companion object {
        fun create(parentView: ViewGroup): ChapterVH {
            return ChapterVH(
                LayoutInflater.from(parentView.context)
                    .inflate(R.layout.item_chapter, parentView, false)
            )
        }
    }
}

class ChapterItemDU : DiffUtil.ItemCallback<ChapterItem>(){
    override fun areItemsTheSame(oldItem: ChapterItem, newItem: ChapterItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ChapterItem, newItem: ChapterItem): Boolean =
        oldItem.name == newItem.name

}