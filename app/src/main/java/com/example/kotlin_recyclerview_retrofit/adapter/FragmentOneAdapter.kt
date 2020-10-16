package com.example.kotlin_recyclerview_retrofit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_recyclerview_retrofit.ActivityWebView
import com.example.kotlin_recyclerview_retrofit.MainActivity
import com.example.kotlin_recyclerview_retrofit.R
import com.example.kotlin_recyclerview_retrofit.models.Articles

class FragmentOneAdapter(val context: Context, val articles: List<Articles>?) : RecyclerView.Adapter<FragmentOneAdapter.MyViewHolder>() {

    val mainActivity = MainActivity()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent , false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val article = articles?.get(position)

        holder.newsTitle.text  = article!!.title
        holder.newsDes.text  = article.description
        Glide
            .with(context)
            .load(article.urlToImage)
            .into(holder.newsImage);
        holder.itemView.setOnClickListener(){
            Toast.makeText(context , article.title , Toast.LENGTH_SHORT).show()
            val intent = Intent(context , ActivityWebView::class.java)
            intent.putExtra("url" , article.url)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.img_view)
        var newsTitle = itemView.findViewById<TextView>(R.id.txt_title)
        var newsDes = itemView.findViewById<TextView>(R.id.txt_des)




    }


}