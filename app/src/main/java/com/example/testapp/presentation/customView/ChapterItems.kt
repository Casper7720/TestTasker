package com.example.testapp.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testapp.R

class ChapterItems : ConstraintLayout {

    private val image: ImageView by lazy { findViewById(R.id.chapter_icon) }
    private val text: TextView by lazy { findViewById(R.id.chapter_name) }
    private val separator: View by lazy { findViewById(R.id.chapter_selector) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context, attrs, 0)
    }

    private fun initLayout(context: Context, attrs: AttributeSet, defStyle: Int) {
        inflate(context, R.layout.item_chapter, this)
        setStyledAttributes(attrs, defStyle)
    }

    private fun setStyledAttributes(attrs: AttributeSet, defStyle: Int) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.ButtonWithDescription, defStyle, 0)

        try {
            val titleText = styledAttributes.getString(R.styleable.ButtonWithDescription_title)
            if (titleText?.isNotBlank() == true) {
                text.text = titleText
            }

            val color = styledAttributes.getColor(R.styleable.ButtonWithDescription_icColor, resources.getColor(R.color.black))
            image.setColorFilter(color)

            val imageDrawable =
                styledAttributes.getDrawable(R.styleable.ButtonWithDescription_image)
            image.setImageDrawable(imageDrawable)

            var typeValue = TypedValue()
            styledAttributes.getValue(R.styleable.ButtonWithDescription_isVisible, typeValue)
            separator.visibility =
                if (typeValue.data != 0)
                    VISIBLE
                else
                    INVISIBLE

        } finally {
            styledAttributes.recycle()
        }
    }
}