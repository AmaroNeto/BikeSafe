package com.openmobility.bike14

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment1.*


class Fragment2: Fragment() {

    var mImageView : ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v : View = inflater.inflate(R.layout.fragment2, container, false)

        val list : MutableList<String> = arrayListOf()
        list.add("Velocidade")
        list.add("Segurança")
        list.add("Agradável")

        val dataAdapter = ArrayAdapter<String>(
            activity,
            android.R.layout.simple_spinner_dropdown_item, list
        )

        val spinner : Spinner = v.findViewById(R.id.spinner)
        spinner.adapter = dataAdapter

        mImageView = v.findViewById(R.id.imageView5)

        val foto : Button = v.findViewById(R.id.button2)
        foto.setOnClickListener(View.OnClickListener {
            dispatchTakePictureIntent()
        })

        return v
    }

    companion object {
        fun newInstance(): Fragment2 = Fragment2()
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity?.packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE /*&& resultCode == activity.RESULT_OK*/) {
            val imageBitmap = data.extras.get("data") as Bitmap
            mImageView?.setImageBitmap(imageBitmap)
        }
    }
}