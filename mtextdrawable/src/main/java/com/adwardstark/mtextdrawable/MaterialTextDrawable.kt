package com.adwardstark.mtextdrawable

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap.Config.ARGB_8888
import android.text.TextPaint
import java.lang.NullPointerException
import java.util.*

/**
 * Created by Aditya Awasthi on 28/09/20.
 * @author github.com/adwardstark
 */

class MaterialTextDrawable private constructor(builder: Builder) {

    companion object {
        private const val MaterialDark = 400
        private const val MaterialMedium = 700
        private const val MaterialLight = 900

        fun with(context: Context): Builder = Builder().with(context)
    }

    enum class MaterialShape {
        CIRCLE,
        RECTANGLE
    }

    enum class MaterialColorMode {
        LIGHT,
        MEDIUM,
        DARK
    }

    private var context: Context
    private var size: Int
    private var colorMode: MaterialColorMode
    private var drawableShape: MaterialShape
    private var text: String

    init {
        this.context = builder.context
        this.size = builder.size
        this.colorMode = builder.colorMode
        this.drawableShape = builder.drawableShape
        this.text = builder.text
    }

    class Builder {

        internal lateinit var context: Context
        internal var size = 150
        internal var colorMode: MaterialColorMode = MaterialColorMode.MEDIUM
        internal var drawableShape: MaterialShape = MaterialShape.CIRCLE
        internal var text: String = ""

        fun with(context: Context): Builder {
            this.context = context
            return this
        }

        fun textSize(size: Int): Builder {
            this.size = size
            return this
        }

        fun shape(shape: MaterialShape): Builder {
            this.drawableShape = shape
            return this
        }

        fun colorMode(mode: MaterialColorMode): Builder {
            this.colorMode = mode
            return this
        }

        fun text(text: String): Builder {
            this.text = text
            return this
        }

        fun getDrawable(): BitmapDrawable {
            if(text == ""){
                throw NullPointerException("No text provided, " +
                        "call text(<your_text>) before calling this method")
            }
            return MaterialTextDrawable(this).getTextDrawable()
        }
    }

    private fun getTextDrawable(): BitmapDrawable {
        val initials = if(text.length > 1){
            getFirstChar(text)
        } else {
            text
        }
        val textPaint = textPainter(calculateTextSize(this.size))
        val painter = Paint()
        painter.isAntiAlias = true

        if(drawableShape == MaterialShape.RECTANGLE) {
            painter.color = ColorGenerator(getColorMode(colorMode)).getRandomColor()
        } else {
            painter.color = Color.TRANSPARENT
        }

        val areaRectangle = Rect(0, 0, size, size)
        val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawRect(areaRectangle, painter)

        if(drawableShape == MaterialShape.RECTANGLE) {
            painter.color = Color.TRANSPARENT
        } else {
            painter.color = ColorGenerator(getColorMode(colorMode)).getRandomColor()
        }

        val bounds = RectF(areaRectangle)
        bounds.right = textPaint.measureText(initials, 0, 1)
        bounds.bottom = textPaint.descent() - textPaint.ascent()

        bounds.left += (areaRectangle.width() - bounds.right) / 2.0f
        bounds.top += (areaRectangle.height() - bounds.bottom) / 2.0f

        canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)
        canvas.drawText(initials, bounds.left, bounds.top - textPaint.ascent(), textPaint)
        return BitmapDrawable(context.resources, bitmap)
    }

    private fun calculateTextSize(size: Int): Float {
        return (size / 4.125).toFloat()
    }

    private fun getFirstChar(text: String): String {
        return text.first().toString().toUpperCase(Locale.ROOT)
    }

    private fun textPainter(size: Float): TextPaint {
        val textPaint = TextPaint()
        textPaint.isAntiAlias = true
        textPaint.textSize = size * context.resources.displayMetrics.scaledDensity
        textPaint.color = Color.WHITE
        return textPaint
    }

    private fun getColorMode(mode: MaterialColorMode): Int {
        return when(mode) {
            MaterialColorMode.LIGHT -> {
                MaterialLight
            }
            MaterialColorMode.MEDIUM -> {
                MaterialMedium
            }
            MaterialColorMode.DARK -> {
                MaterialDark
            }
        }
    }
}