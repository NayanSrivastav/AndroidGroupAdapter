package com.example.groupadapter.sample.dataProvider

import com.example.groupadapter.adapter.IAdapterData
import com.example.groupadapter.adapter.IAdapterViewProvider
import com.example.groupadapter.sample.adapterProvider.MoviesProvider

class Movie(
    var movieId: Int,
    var movieTitle: String,
    var movieViews: Int,
    var releaseDate: String,
    var moviePicture: Int,
    override val provider: IAdapterViewProvider
) : IAdapterData

