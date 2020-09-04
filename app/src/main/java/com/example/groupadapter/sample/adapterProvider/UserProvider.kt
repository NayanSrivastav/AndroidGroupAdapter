package com.example.groupadapter.sample.adapterProvider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groupadapter.R
import com.example.groupadapter.adapter.AdapterViewInfo
import com.example.groupadapter.adapter.IAdapterData
import com.example.groupadapter.adapter.IAdapterViewProvider
import com.example.groupadapter.sample.dataProvider.User

class UserProvider(private val context: Context) : IAdapterViewProvider {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv = itemView.findViewById<TextView>(R.id.nameTV)
    }

    override fun bindView(viewHolder: RecyclerView.ViewHolder, data: IAdapterData) {
        if (data is User && viewHolder is UserViewHolder) {
            viewHolder.nameTv.text = data.name
        }
    }

    override fun inflateView(parentView: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.username, parentView, false)
    }

    override fun getViewHolder(view: View) = UserViewHolder(view)


    override fun getType() = AdapterViewInfo.USERS

    override fun getColumnCount() = 1
}