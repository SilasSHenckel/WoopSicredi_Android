package br.com.testesicredi.model.request

import com.google.gson.annotations.SerializedName

class CheckIn(eventId: Int, name: String, email: String) {
    @SerializedName("eventId")
    private val eventId: Int? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("email")
    private val email: String? = null
}