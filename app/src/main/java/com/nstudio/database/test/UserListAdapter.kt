package com.nstudio.database.test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class UserListAdapter(private val list: ArrayList<User>): RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return MyViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {

        viewHolder.tvName.text = list[position].name
        viewHolder.tvEmail.text = list[position].email
        viewHolder.tvMobile.text = list[position].mobile

    }


    inner class MyViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName : TextView = itemView.findViewById(R.id.tvName)
        var tvEmail : TextView = itemView.findViewById(R.id.tvEmail)
        var tvMobile : TextView = itemView.findViewById(R.id.tvMobile)
    }
}

