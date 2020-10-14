package br.com.testesicredi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.testesicredi.R
import br.com.testesicredi.controller.SicrediController
import br.com.testesicredi.model.response.Event
import br.com.testesicredi.view.adapters.EventsAdapter
import kotlinx.android.synthetic.main.events_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsActivityKotlin : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)
        buildLayout()
        onSwipeRefresh()
    }

    fun buildLayout() {
        setToolbar()
        setSwipeRefresh()
    }

    fun setToolbar() {
        setSupportActionBar(events_activity_toolbar)
    }

    fun setSwipeRefresh() {
        events_activity_swipe?.isRefreshing = true
        events_activity_swipe?.setOnRefreshListener { onSwipeRefresh() }
    }

    fun onSwipeRefresh() {
        events_activity_swipe?.isRefreshing = true
        getEvents()
    }

    fun hideSwipeRefresh() {
        events_activity_swipe?.isRefreshing = false
    }

    fun getEvents() {
        SicrediController.getInstance().sicrediService.events.enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful) response.body()?.let { setEventsAdapter(it) }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                hideSwipeRefresh()
            }
        })
    }

    fun setEventsAdapter(events: List<Event>) {
        val rvEvents = events_activity_recycler
        rvEvents.layoutManager = LinearLayoutManager(applicationContext)
        val eventsAdapter = EventsAdapter(events, this)
        rvEvents.adapter = eventsAdapter
        hideSwipeRefresh()
    }
}