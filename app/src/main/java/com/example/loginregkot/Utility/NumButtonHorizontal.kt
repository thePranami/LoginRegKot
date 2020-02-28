package com.example.loginregkot.Utility

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.loginregkot.R
import com.google.gson.internal.bind.ArrayTypeAdapter

class NumButtonHorizontal() : RelativeLayout() {
    lateinit var context:Context
    private var attrs: AttributeSet? = null
    private val styleAttr: Int
    private var mListener: OnClickListener? = null
    private var initialNumber: Int = 0
    private var lastNumber: Int = 0
    private var currentNumber: Int = 0
    private var finalNumber: Int = 0
    private var textView: TextView? = null
    private var addBtn: Button? = null, private var subtractBtn:Button? = null
    private var view: View? = null
    private var mOnValueChangeListener: NumberPicker.OnValueChangeListener? = null

    constructor(context: Context):this(){
        context = context
    }

    constructor(context: Context, attributeSet: AttributeSet):this(){

    }
    constructor(context: Context, attributeSet: AttributeSet, defAttrt : Int):this(){
        var context=context
    }

    private fun init(){
        this.view =this
        View.inflate(context, R.layout.num_button_horizontal, this)
        resources
        = context.obtainStyledAttributes()
    }

}