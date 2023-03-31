package com.example.testapp.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testapp.R

class ButtonWithDescription : ConstraintLayout {

    private val title: TextView by lazy { findViewById(R.id.title) }
    private val description: TextView by lazy { findViewById(R.id.description) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context, attrs, 0)
    }

    private fun initLayout(context: Context, attrs: AttributeSet, defStyle: Int) {
        inflate(context, R.layout.item_button_with_description, this)
        setStyledAttributes(attrs, defStyle)
    }

    private fun setStyledAttributes(attrs: AttributeSet, defStyle: Int) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.ButtonWithDescription, defStyle, 0)

        try {
            val titleText = styledAttributes.getString(R.styleable.ButtonWithDescription_title)
            if (titleText?.isNotBlank() == true) {
                title.text = titleText
            }

            val descriptionText =
                styledAttributes.getString(R.styleable.ButtonWithDescription_description)
            if (descriptionText?.isNotBlank() == true) {
                description.text = descriptionText
            }
        } finally {
            styledAttributes.recycle()
        }
    }
}