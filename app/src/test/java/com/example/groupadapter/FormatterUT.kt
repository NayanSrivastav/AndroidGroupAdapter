package com.example.groupadapter;

import com.example.groupadapter.adapter.UpdateFormatter
import com.example.groupadapter.adapter.UpdateType
import org.junit.Test;

class FormatterUT {

    @Test
    fun test() {
        val formatter = UpdateFormatter<Int>()
        val source = mutableListOf(1, 5, 7)

        // for append at pos
        var list = listOf(2, 3)

        formatter.format(source, list, UpdateType.APPEND(1))
        assert(source.size == 5)

        list = listOf(8, 9)
        formatter.format(source, list, UpdateType.APPEND(null))
        assert(source.size == 7)

        // prepend
        list = listOf(11, 13)
        formatter.format(source, list, UpdateType.PREPEND(6))
        assert(source.size == 9)

        // replace
        list = listOf(14, 15)
        formatter.format(source, list, UpdateType.REPLACE(5, 7))
        assert(source.size == 9)

    }
}
