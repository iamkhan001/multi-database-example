package com.nstudio.database.test

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.util.*

class DbHandler(val context: Context ){


    internal fun createDbApp(dbName: String): Boolean {
        val dataBase = DataBaseApp(context)
        val dbb = dataBase.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("dbName", dbName)
        return dbb.insert("databaseNames", null, contentValues) > 0
    }

    internal fun addUser(dbName: String, user: User): Boolean {
        Log.e("DbHandler", "DB $dbName")

        val dataBase = DataBaseUser(context, dbName)

        val dbb = dataBase.writableDatabase
        val contentValues = ContentValues()

        contentValues.put("name", user.name)
        contentValues.put("email", user.email)
        contentValues.put("mobile", user.mobile)
        var count = 0L

        try {
            count = dbb.insert("users", null, contentValues)
        }catch (e: Exception){
            e.printStackTrace()
        }

        Log.e("DbHandler", "count > $count")
        return count > 0
    }

    internal fun getDatabaseNames() : ArrayList<String> {

        val dataBase = DataBaseApp(context)

        val list = ArrayList<String>()
        val columns = arrayOf("dbName")

        try {
            val db = dataBase.readableDatabase
            val cursor = db.query("databaseNames", columns, null, null, null, null, null)

            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex("dbName")))
            }
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }




        return list
    }


    internal fun getUserList(dbName: String) : ArrayList<User> {

        Log.e("DbHandler", "DB $dbName")
        val dataBase = DataBaseUser(context,dbName)

        val list = ArrayList<User>()

        try {
            val db = dataBase.readableDatabase


            val cursor = db.query("users", null, null, null, null, null, null)

            while (cursor.moveToNext()) {
                val user = User(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("mobile"))
                )
                list.add(user)
            }
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }


        return list
    }

}