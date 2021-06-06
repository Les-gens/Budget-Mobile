package com.example.budget.ui.photo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import androidx.core.app.ActivityCompat
import com.example.budget.R
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class PhotoFragment : Fragment() {


    companion object {
        fun newInstance() = PhotoFragment()
    }

    private lateinit var viewModel: PhotoViewModel
    private lateinit var button : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.fragment_photo, container, false)
        button = root.findViewById<ImageButton>(R.id.btnPhoto)
        button.isEnabled = false

        if (ActivityCompat.checkSelfPermission(
                root.context,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.CAMERA),
                    111
                )
            }
        } else {
            button.isEnabled = true
            button.setOnClickListener {
                var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(i, 101)
            }
        }

        val listView = root.findViewById<ListView>(R.id.photos_view)
        context?.let {
            val adapter: PhotoAdapter = PhotoAdapter(it, getPhotos())
            listView.adapter = adapter
        }

        return root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            button.isEnabled = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            var path = saveToInternalStorage(pic!!)
            context?.let {
                val listView = activity?.findViewById<ListView>(R.id.photos_view)
                val adapter: PhotoAdapter = PhotoAdapter(it, getPhotos())
                listView?.adapter = adapter
            }
        }
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap): String? {
        //val cw = ContextWrapper()

        val directory: File = context?.getDir("imageDir", Context.MODE_PRIVATE) ?: return null
        var tmp = UUID.randomUUID().toString()
        val mypath = File(directory, "profile_$tmp.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.getAbsolutePath()
    }

    private fun getPhotos(): ArrayList<File> {
        val files = context?.getDir("imageDir", Context.MODE_PRIVATE)?.listFiles()
        val al = ArrayList<File>()
        try {
          files?.forEach { file -> al.add(file) }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return al
    }
}