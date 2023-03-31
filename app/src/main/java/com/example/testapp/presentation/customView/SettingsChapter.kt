package com.example.testapp.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testapp.R
import com.google.android.material.button.MaterialButton

class SettingsChapter : ConstraintLayout {

    val button: MaterialButton by lazy { findViewById(R.id.settings_chapter_button) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context, attrs, 0)
    }

    private fun initLayout(context: Context, attrs: AttributeSet, defStyle: Int) {
        inflate(context, R.layout.item_settings_chapter, this)
        setStyledAttributes(attrs, defStyle)
    }

    private fun setStyledAttributes(attrs: AttributeSet, defStyle: Int) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.SettingsChapter, defStyle, 0)

        try {
            val text = styledAttributes.getString(R.styleable.SettingsChapter_text)
            if (text?.isNotBlank() == true) {
                button.text = text
            }

            val startIcon = styledAttributes.getDrawable(R.styleable.SettingsChapter_startIcon)

            val rightIcon = context.getDrawable(R.drawable.ic_chevron_right)
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(
                startIcon, null, rightIcon, null
            )
        } finally {
            styledAttributes.recycle()
        }


    }
}