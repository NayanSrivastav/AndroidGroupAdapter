package com.example.groupadapter.adapter

sealed class UpdateType {
    class APPEND(val position: Int? = null) : UpdateType()

    /**
     * replaces entire list with the new items,
     * pass [to] and [from] both to replace a specific range
     *
     */
    class REPLACE(val from: Int? = null, val to: Int? = null) : UpdateType() {

        fun isRangeWithInBounds(source: List<*>): Boolean {
            if (to == null || from == null) {
                return true
            }

            if (to > source.size || from < 0 || to <= from) {
                return false
            }

            return true
        }
    }

    class PREPEND(val position: Int? = null) : UpdateType()
}

interface OnUpdateListener<T> {
    fun onUpdate(items: List<T>, type: UpdateType)
}