package com.nstudio.database.test

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import android.widget.LinearLayout



class CreateDbDialog(context: Context, private val cancellable : Boolean, private val onDbCreateListener: OnDbCreateListener) : Dialog(context) {


    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_db)

        this.window.setGravity(Gravity.CENTER)

        val editDbName = findViewById<EditText>(R.id.editDbName)

        if (cancellable){
            findViewById<View>(R.id.btnCancel).setOnClickListener {
                editDbName.setText("")
                dismiss()
            }
        }else{

            val param = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            )
            findViewById<View>(R.id.btnCreate).layoutParams = param
            findViewById<View>(R.id.btnCancel).visibility = View.GONE
        }

        findViewById<View>(R.id.btnCreate).setOnClickListener {

            val name : String = editDbName.text.toString().trim()


            if (name.isEmpty()){
                editDbName.error = "Enter Database Name"
                return@setOnClickListener
            }

            val handler = DbHandler(context)

            if (handler.createDbApp(name)){
                onDbCreateListener.onDbCreate(true)
                Toast.makeText(context,"Database Created Successfully",Toast.LENGTH_SHORT).show()
                dismiss()
            }else{
                Toast.makeText(context,"Unable to create Database",Toast.LENGTH_SHORT).show()
            }


        }

    }

    interface OnDbCreateListener{
        fun onDbCreate(success : Boolean)
    }

}