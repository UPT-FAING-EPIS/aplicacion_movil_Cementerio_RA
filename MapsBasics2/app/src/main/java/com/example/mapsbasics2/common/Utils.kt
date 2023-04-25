package com.example.mapsbasics2.common


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.viewbinding.ViewBinding
import com.example.mapsbasics2.R
import android.content.Context


object Utils {
    var binding: ViewBinding? = null

    fun dp(px: Int): Int{
        if (binding == null) return 0

        val scale = binding!!.root.resources.displayMetrics.density
        return (scale * px + 0.5f).toInt()
    }

    fun getBitmapFromVector(context: Context, resId: Int): Bitmap?{
        return AppCompatResources.getDrawable(context, resId)?.toBitmap()
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap{
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if(bitmapRatio > 1){
            width = maxSize
            height = (width / bitmapRatio).toInt()
        }
        else{
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        val imgResult = Bitmap.createBitmap(maxSize, maxSize, Bitmap.Config.ARGB_8888)
        val imgResized = Bitmap.createScaledBitmap(image, width, height, true)

        val canvas = Canvas(imgResult)
        canvas.drawBitmap(imgResized, 0f, 0f, null)
        val stroke = 4
        val radius = maxSize / 2 - stroke / 2
        val coordinate = (maxSize / 2).toFloat()
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        //paint.color = ContextCompat.getColor(context, R.color.teal_200)
        paint.strokeWidth = stroke.toFloat()
        canvas.drawCircle(coordinate, coordinate, radius.toFloat(), paint)

        return imgResult
    }
}