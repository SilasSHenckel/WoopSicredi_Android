package br.com.testesicredi.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.testesicredi.R
import br.com.testesicredi.controller.SicrediController
import br.com.testesicredi.model.response.Event
import br.com.testesicredi.utils.Utils
import br.com.testesicredi.view.EventDetailsActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class EventsAdapter(private val mEvents: List<Event>, private val mContext: Context?) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_item_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindEvent(mEvents[position], holder.itemView)
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_event_title)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_event_price)
        var ivEvent: ImageView = itemView.findViewById(R.id.ivEvent)

        fun bindEvent(event: Event, itemView: View) {
            tvTitle.text = event.title
            tvPrice.text = Utils.formatCurrency((if (event.price != null) event.price else 0.0)!!)

            Glide.with(itemView.context)
                    .load(event.imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.no_image)
                    .into(ivEvent)

            itemView.rootView.setOnClickListener { startEventDetailsActivity(event) }
        }

        fun startEventDetailsActivity(event: Event) {
            if (mContext != null) {
                SicrediController.getInstance().eventSelected = event.id
                mContext.startActivity(Intent(mContext, EventDetailsActivity::class.java))
            }
        }
    }

}