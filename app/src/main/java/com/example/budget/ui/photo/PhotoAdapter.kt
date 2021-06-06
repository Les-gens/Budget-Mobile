package com.example.budget.ui.photo

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.budget.R
import java.io.File
import java.io.FileInputStream

class PhotoAdapter(private val context: Context, private val imageIdList: ArrayList<File>) : BaseAdapter() {
    override fun getCount(): Int {
        return imageIdList.size
    }

    override fun getItem(position: Int): Any {
        return imageIdList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.photo_row, parent, false)
        val imageView = convertView.findViewById<ImageView>(R.id.photoImageView)
        val file: File = imageIdList[position]
        val b = BitmapFactory.decodeStream(FileInputStream(file))
        imageView.setImageBitmap(b)

        return convertView
    }


}