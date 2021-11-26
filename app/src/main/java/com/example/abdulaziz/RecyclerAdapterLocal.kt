package com.example.abdulaziz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abdulaziz.data.ShowEntity
import org.json.JSONObject


class RecyclerAdapterLocal(private val activity: fragment_db): RecyclerView.Adapter<RecyclerAdapterLocal.ViewHolder>() {
    private var shows = emptyList<ShowEntity>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var bt_delete: Button
        val tv_title: TextView
        val tv_lang: TextView
        val img_show: ImageView
        val card_show: LinearLayout

        init {
            bt_delete = itemView.findViewById(R.id.bt_delete)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_lang = itemView.findViewById(R.id.tv_lang)
            img_show = itemView.findViewById(R.id.img_show)
            card_show = itemView.findViewById(R.id.card_show)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.local_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterLocal.ViewHolder, position: Int) {
        val id: Int = shows[position].id
        val showName: String = shows[position].showName
        val showLanguage: String = shows[position].showLanguage
        val showSummary: String = shows[position].showSummary
        val showImageURL: String = shows[position].showImageURL

        if (showImageURL.isNullOrBlank()) {
            holder.img_show.setImageResource(R.drawable.ic_movie_svgrepo_com)
        } else {
            Glide.with(activity)
                .load(showImageURL)
                .into(holder.img_show)
        }
        holder.tv_title.setText(showName)
        holder.tv_lang.setText(showLanguage)

        holder.bt_delete.setOnClickListener {
            activity.deleteAShow(id)
        }
        holder.card_show.setOnClickListener {
            Toast.makeText(activity.context, "$showSummary", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount() = shows.size

    fun update(shows: List<ShowEntity>){
        println("UPDATING DATA")
        this.shows = shows
        notifyDataSetChanged()
    }
}