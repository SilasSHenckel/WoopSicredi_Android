package br.com.testesicredi.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.testesicredi.R
import br.com.testesicredi.controller.SicrediController
import br.com.testesicredi.model.response.Event
import br.com.testesicredi.view.adapters.PeopleAdapter
import br.com.testesicredi.view.dialog.CheckInDialogFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_details_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_details_activity)
        buildLayout()
        onSwipeRefresh()
    }

    fun buildLayout() {
        setToolbar()
        setSwipeRefresh()
        setFab()
    }

    fun setFab() {
        val fab = event_details_activity_fab
        fab?.setOnClickListener { CheckInDialogFragment().show(supportFragmentManager, getString(R.string.check_in_dialog_fragment)) }
    }

    fun setToolbar() {
        setSupportActionBar(event_details_activity_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    fun setSwipeRefresh() {
        event_details_activity_swipe?.isRefreshing = true
        event_details_activity_swipe?.setOnRefreshListener { onSwipeRefresh() }
    }

    fun onSwipeRefresh() {
        event_details_activity_swipe?.isRefreshing = true
        getEvent()
    }

    fun hideSwipeRefresh() {
        event_details_activity_swipe?.isRefreshing = false
    }

    fun getEvent() {
        SicrediController.getInstance().sicrediService
                .getEventById(SicrediController.getInstance().eventSelected.toString()).enqueue(object : Callback<Event?> {
                    override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                        if (response.isSuccessful) onGetEvent(response.body())
                    }

                    override fun onFailure(call: Call<Event?>, t: Throwable) {
                        showNotFound(View.VISIBLE)
                        hideSwipeRefresh()
                    }
                })
    }

    fun onGetEvent(event: Event?) {
        setDescription(event)
        setPeopleAdapter(event)
        setEventImage(event)
        hideSwipeRefresh()
    }

    fun setEventImage(event: Event?) {
        if (event != null)
            Glide.with(layout_root.context)
                    .load(event.imageUrl)
                    .into(event_details_activity_event)
    }

    fun setDescription(event: Event?) {
        val tvDescription = event_details_activity_tv_description
        if (event != null && tvDescription != null) {
            if (event.description != null && event.description!!.isNotEmpty()) {
                tvDescription.text = event.description
                tvDescription.visibility = View.VISIBLE
            } else {
                tvDescription.visibility = View.GONE
            }
        }
    }

    fun setPeopleAdapter(event: Event?) {
        val rvPeople = event_details_activity_recycler
        if (event != null && !event.people.isNullOrEmpty()) {
            rvPeople.layoutManager = LinearLayoutManager(applicationContext)
            val peopleAdapter = PeopleAdapter(event.people!!)
            rvPeople.adapter = peopleAdapter
            showNotFound(View.GONE)
        } else {
            showNotFound(View.VISIBLE)
        }
    }

    fun showNotFound(visibility: Int) {
        event_details_activity_tv_no_shows?.visibility = visibility
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) finish()
        return true
    }

}