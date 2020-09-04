package com.example.groupadapter.sample.dataProvider

import com.example.groupadapter.adapter.IAdapterData
import com.example.groupadapter.adapter.IAdapterViewProvider

data class User(val name: String, override val provider: IAdapterViewProvider) : IAdapterData