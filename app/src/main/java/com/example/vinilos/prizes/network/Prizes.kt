package com.example.vinilos.prizes.network

import androidx.lifecycle.LiveData

/**
 * Gets Mars real estate artist information from the Mars API Retrofit service and updates the
 * [Prize] and [PrizeApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 */
data class Prize(
        val name: String,
        val description: String,
        val organization: String)
