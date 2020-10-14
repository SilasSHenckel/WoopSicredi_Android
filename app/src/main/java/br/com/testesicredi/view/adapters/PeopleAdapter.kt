package br.com.testesicredi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.testesicredi.R
import br.com.testesicredi.model.response.Person
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PeopleAdapter(private val mPeople: List<Person>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.people_item_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindEvent(mPeople[position], holder.itemView)
    }

    override fun getItemCount(): Int {
        return mPeople.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvPeople: TextView?
        var ivPeople: ImageView

        fun bindEvent(person: Person, itemView: View) {
            tvPeople!!.text = person.name
            Glide.with(itemView.context)
                    .load(person.picture)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.no_image)
                    .into(ivPeople)
        }

        init {
            tvPeople = itemView.findViewById(R.id.tv_people)
            ivPeople = itemView.findViewById(R.id.iv_people)
        }
    }

}