package com.example.testapp.presentation.dialogs.choiceDialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R

class ChoiceLanguageAdapter: ListAdapter<String, ChoiceLanguageVH>(StringDU()) {

    private var list: List<String> = ArrayList()
    private var listener: ChoiceLanguageVH.LanguageListener? = null

    fun setData(list: List<String>) {
        this.list = list
        submitList(this.list)
    }

    fun setListener(listener: ChoiceLanguageVH.LanguageListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceLanguageVH {
        return ChoiceLanguageVH.create(parent)
    }

    override fun onBindViewHolder(holder: ChoiceLanguageVH, position: Int) {
        return holder.bind(list[position], listener)
    }
}

class ChoiceLanguageVH(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: String, listener: LanguageListener?) {
        val name = view.findViewById<TextView>(R.id.name_language)

        name.text = item

        view.rootView.setOnClickListener {
            listener?.onClick(item)
        }
    }

    interface LanguageListener{
        fun onClick(language: String)
    }

    companion object {
        fun create(parentView: ViewGroup): ChoiceLanguageVH {
            return ChoiceLanguageVH(
                LayoutInflater.from(parentView.context)
                    .inflate(R.layout.item_language, parentView, false)
            )
        }
    }
}

class StringDU : DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}