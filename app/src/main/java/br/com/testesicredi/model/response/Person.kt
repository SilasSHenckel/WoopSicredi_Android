package br.com.testesicredi.model.response

import com.google.gson.annotations.SerializedName

class Person {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("eventId")
    var eventId: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("picture")
    var picture: String? = null

}