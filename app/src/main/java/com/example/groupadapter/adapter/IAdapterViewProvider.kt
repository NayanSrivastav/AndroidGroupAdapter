package com.example.groupadapter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

interface IAdapterViewProvider {
    fun bindView(viewHolder: RecyclerView.ViewHolder, data: IAdapterData)
    fun inflateView(parentView: ViewGroup): View
    fun getViewHolder(view: View): RecyclerView.ViewHolder

    /**
     * should be from values registered in [AdapterViewInfo], for new provider register a new unique value
     */
    fun getType(): Int

    fun getColumnCount(): Int
}

object AdapterViewInfo {

    // static immutable registry
    private val providers: MutableMap<Int, IAdapterViewProvider> = mutableMapOf()

    // view types start
    const val MOVIES = 1
    const val USERS = 2
    // view types end

    fun registerProvider(type: Int, provider: IAdapterViewProvider) {
        if (providers.containsKey(type)) {
            throw IllegalArgumentException("type $type is already registered")
        }
        providers[type] = provider
    }

    fun getProviderByType(type: Int): IAdapterViewProvider {
        return providers.getOrElse(type) {
            throw IllegalStateException("type $type is not registered")
        }
    }
}