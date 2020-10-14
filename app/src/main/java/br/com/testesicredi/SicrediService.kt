package br.com.testesicredi

import br.com.testesicredi.model.request.CheckIn
import br.com.testesicredi.model.response.Event
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SicrediService {
    @get:GET("events")
    val events: Call<List<Event>>

    @GET("events/{id}")
    fun getEventById(@Path("id") id: String?): Call<Event>

    @POST("checkin")
    fun checkIn(@Body form: CheckIn?): Call<CheckIn?>?
}