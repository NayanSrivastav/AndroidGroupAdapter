package com.example.groupadapter.sample.adapterProvider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groupadapter.R
import com.example.groupadapter.adapter.AdapterViewInfo
import com.example.groupadapter.adapter.IAdapterData
import com.example.groupadapter.adapter.IAdapterViewProvider
import com.example.groupadapter.sample.dataProvider.Movie

class MoviesProvider(private val context: Context) : IAdapterViewProvider {

    override fun bindView(viewHolder: RecyclerView.ViewHolder, data: IAdapterData) {
        if (data is Movie && viewHolder is MoviesViewHolder) {
            viewHolder.titleTv.text = data.movieTitle
            viewHolder.imageView.setImageResource(data.moviePicture)
            viewHolder.releaseDateTv.text = data.releaseDate
            viewHolder.viewsTv.text = "Views ${data.movieViews}"
        }
    }

    override fun inflateView(parentView: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.movies, parentView, false)
    }

    override fun getViewHolder(view: View): RecyclerView.ViewHolder {
        return MoviesViewHolder(view)
    }

    override fun getType(): Int {
        return AdapterViewInfo.MOVIES
    }

    override fun getColumnCount(): Int {
        return 2
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageMovie)
        val titleTv: TextView = itemView.findViewById(R.id.textMovieTitle)
        val viewsTv: TextView = itemView.findViewById(R.id.textMovieViews)
        val releaseDateTv: TextView = itemView.findViewById(R.id.textReleaseDate)
    }
}