package com.example.testapp.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testapp.R
import com.google.android.material.button.MaterialButton

class ButtonText : LinearLayout {

    val title: TextView by lazy { findViewById(R.id.title) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context, attrs, 0)
    }

    private fun initLayout(context: Context, attrs: AttributeSet, defStyle: Int) {
        inflate(context, R.layout.item_text, this)
        setStyledAttributes(attrs, defStyle)
    }

    private fun setStyledAttributes(attrs: AttributeSet, defStyle: Int) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.SettingsChapter, defStyle, 0)

        try {
            val text = styledAttributes.getString(R.styleable.SettingsChapter_text)
            if (text?.isNotBlank() == true) {
                title.text = text
            }
        } finally {
            styledAttributes.recycle()
        }


    }
}