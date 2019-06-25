package com.nstudio.database.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:AppCompatActivity(){


    lateinit var dbNameList : ArrayList<String>
    var dbName : String = ""
    lateinit var dbHandler : DbHandler
    lateinit var rvUsers : RecyclerView
    lateinit var list : ArrayList<User>
    lateinit var userAdapter : UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val editName = findViewById<EditText>(R.id.editUserName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editMobile = findViewById<EditText>(R.id.editMobile)
        val spnDbNames = findViewById<Spinner>(R.id.spnDbNames)

        rvUsers = findViewById(R.id.rvUsers)

        dbHandler = DbHandler(this@MainActivity)

        dbNameList = dbHandler.getDatabaseNames()


        val listener = object : CreateDbDialog.OnDbCreateListener {
            override fun onDbCreate(success: Boolean) {
                if (success){

                    dbNameList = dbHandler.getDatabaseNames()
                    val adapter = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item, dbNameList)
                    spnDbNames.adapter = adapter

                }
            }
        }

        if(dbNameList.size == 0){

            val createDbDialog = CreateDbDialog(this@MainActivity, false, listener)
            createDbDialog.setCancelable(false)
            createDbDialog.show()

        }else {

            val adapter = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item, dbNameList)
            spnDbNames.adapter = adapter

        }

        spnDbNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                dbName = dbNameList[position]
                showUserList()


            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val name = editName.text.trim()
            val email = editEmail.text.trim()
            val mobile = editMobile.text.trim()

            var isValid = true

            if(name.isEmpty()){
                editName.error = "Enter Name"
                isValid = false
            }

            if(email.isEmpty()){
                editEmail.error = "Enter Email"
                isValid = false
            }

            if(mobile.isEmpty()){
                editMobile.error = "Enter Mobile"
                isValid = false
            }

            if (isValid){

                if(dbName.isNotEmpty()){
                    val user = User(0,name.toString(),email.toString(),mobile.toString())
                    if(dbHandler.addUser(dbName,user)){
                        if(list.size ==0){
                            showUserList()
                        }else{
                            list.add(user)
                            userAdapter.notifyItemInserted(list.size-1)
                        }


                        Toast.makeText(this@MainActivity,"User Details Saved Successfully!",Toast.LENGTH_SHORT).show()

                        editName.setText("")
                        editMobile.setText("")
                        editEmail.setText("")

                    }else{
                        Toast.makeText(this@MainActivity,"Error Occurred!",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@MainActivity,"Database is not created!",Toast.LENGTH_SHORT).show()
                }


            }

        }

        findViewById<View>(R.id.fabCreateDb).setOnClickListener {
            val createDbDialog = CreateDbDialog(this@MainActivity, true, listener)
            createDbDialog.show()
        }



        spnDbNames.setSelection(0)

    }

    fun showUserList(){
        list = dbHandler.getUserList(dbName)
        userAdapter = UserListAdapter(list)

        Log.e("MainActivity","count users > ${list.size}")

        val manager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvUsers.layoutManager = manager
        rvUsers.adapter = userAdapter

        userAdapter.notifyDataSetChanged()
    }

}