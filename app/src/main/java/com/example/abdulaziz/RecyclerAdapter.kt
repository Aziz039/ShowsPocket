package com.example.abdulaziz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.lang.Exception

class RecyclerAdapter(private val activity: fragment_api): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var shows = ArrayList<JSONObject>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var bt_show: Button

        init {
            bt_show = itemView.findViewById(R.id.bt_show)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val showId:  Int = shows[position].getJSONObject("show").getInt("id")
        val showName: String = shows[position].getJSONObject("show").getString("name")
        var showSummary: String = ""
        val showLanguage: String = shows[position].getJSONObject("show").getString("language")
        var showImageURL: String = ""
        try {
            showSummary = shows[position].getJSONObject("show").getString("summary")
            showImageURL = shows[position].getJSONObject("show").getJSONObject("image").getString("medium")
        } catch (e: Exception) {
            Log.d("Logger_api", "No image")
        }

        holder.bt_show.setText(showName)

        holder.bt_show.setOnClickListener {
            activity.addToDB(showId, showName,showSummary, showLanguage, showImageURL)
        }
    }

    override fun getItemCount() = shows.size

    fun update(shows: ArrayList<JSONObject>){
        println("UPDATING DATA")
        this.shows = shows
        notifyDataSetChanged()
    }
}