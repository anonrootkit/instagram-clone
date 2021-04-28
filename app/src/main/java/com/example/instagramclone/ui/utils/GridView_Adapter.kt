package com.example.instagramclone.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.instagramclone.R

data class Post(
    var postURl : String
)

class ArrayAdapter_GridView(context : Context, values: ArrayList<Post>) : ArrayAdapter<Post>(context,0,values){
    override fun getView(position: Int, initialView: View?, parent: ViewGroup): View {
        var view : View? = initialView
        if (initialView==null){
            view = LayoutInflater.from(parent.context).inflate(R.layout.gridview, parent, false)
        }

        var post_in_GridView : ImageView = view!!.findViewById(R.id.user_post)

        var post : Post = getItem(position)!!

        Glide.with(view).load(post.postURl).into(post_in_GridView)






        return view

    }
}