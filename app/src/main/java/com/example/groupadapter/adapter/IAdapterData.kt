package com.example.groupadapter.adapter

interface IAdapterData {
    val provider: IAdapterViewProvider
}

/**
 *
 * utility class to wrap IAdapterData and model
 */
data class IAdapterDataImpl<T>(override val provider: IAdapterViewProvider, val data: T) : IAdapterData