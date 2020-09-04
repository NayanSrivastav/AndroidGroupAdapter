package com.example.groupadapter.adapter

import java.lang.IllegalStateException

class UpdateFormatter<T> {

    fun format(
        source: MutableList<T>,
        items: List<T>?,
        type: UpdateType
    ) {

        synchronized(UpdateFormatter::class) {
            formatData(source, items, type)
        }
    }

    private fun formatData(source: MutableList<T>,
                           items: List<T>?,
                           type: UpdateType){

        if (source.isEmpty()) {
            items?.let {
                source.addAll(items)
            }
            return
        }

        items?.let {
            when (type) {
                is UpdateType.REPLACE -> {
                    var newList = items;
                    if (type.from != null && type.to != null) {

                        if (!type.isRangeWithInBounds(source)) {
                            throw IllegalStateException("${type.to} and ${type.from} not a valid range, source size=${source.size}")
                        }

                        val fromList = source.subList(0, type.from)
                        val toList = source.subList(type.to, source.size)
                        newList = fromList + items + toList
                    }
                    source.clear()
                    source.addAll(newList)
                }

                is UpdateType.APPEND -> {
                    source.addAll(type.position ?: source.size, items)
                }

                is UpdateType.PREPEND -> {
                    var pos = type.position ?: 0
                    if (pos < 0 || pos > source.size) {
                        pos = 0
                    }
                    source.addAll(pos, items)
                }
            }
        }
    }
}