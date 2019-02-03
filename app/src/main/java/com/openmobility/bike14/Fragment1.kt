package com.openmobility.bike14

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class Fragment1: Fragment() {

   // var button : Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment1, container, false)
        val button : Button = v.findViewById(R.id.button)
        val edit: EditText = v.findViewById(R.id.editText)

        button.setOnClickListener(View.OnClickListener {
            Log.d("TESTE","FOI")

            var fragment : LoadFragment = LoadFragment()
            fragment.setAddress(edit.text.toString())

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container,fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })

        return v
    }
    companion object {
        fun newInstance(): Fragment1 = Fragment1()
    }
}