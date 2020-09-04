package com.example.groupadapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView


class GroupAdapter(data: List<IAdapterData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    OnUpdateListener<IAdapterData> {

    private val data: MutableList<IAdapterData> = data.toMutableList()
    private val formatter =
        UpdateFormatter<IAdapterData>()

    private fun getProviderByPosition(position: Int): IAdapterViewProvider {
        return data[position].provider
    }

    override fun getItemViewType(position: Int): Int {
        return getProviderByPosition(position).getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val provider = AdapterViewInfo.getProviderByType(viewType)

        // holding view here to have more control on appearance and can manipulate the look and feel
        val view = provider.inflateView(parent)

        return provider.getViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewItem = data[position]
        getProviderByPosition(position).bindView(holder, viewItem)
    }

    var onSpanSizeLookup: SpanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return getProviderByPosition(position).getColumnCount()
        }
    }

    override fun onUpdate(
        items: List<IAdapterData>,
        type: UpdateType
    ) {
        formatter.format(data, items, type)
        notifyDataSetChanged()
    }
}