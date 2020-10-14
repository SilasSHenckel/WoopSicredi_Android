package br.com.testesicredi.model.response

import com.google.gson.annotations.SerializedName

class Event {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("price")
    var price: Double? = null

    @SerializedName("latitude")
    var latitude: String? = null

    @SerializedName("longitude")
    var longitude: String? = null

    @SerializedName("image")
    var imageUrl: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("date")
    var date: Long? = null

    @SerializedName("people")
    var people: List<Person>? = null

}