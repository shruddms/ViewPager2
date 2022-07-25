package com.kyungeun.viewpager2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kyungeun.viewpager2.databinding.ItemSliderBinding

class SliderAdpater internal constructor(
    sliderItems: ArrayList<SliderItem>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<SliderViewHolder>() {

    private val sliderItems: ArrayList<SliderItem>

    init {
        this.sliderItems = sliderItems
    }

    interface OnItemClickListener {
        fun onClicked(item: SliderItem)
        fun onClickedTitle(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding: ItemSliderBinding =
            ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = sliderItems.size

//  override fun onBindViewHolder(holder: SliderViewHolder, position: Int) = holder.bind(sliderItems[position])
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) = holder.bind(sliderItems[position], sliderItems.size)
}

class SliderViewHolder(
    private val itemBinding: ItemSliderBinding,
    private val listener: SliderAdpater.OnItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var sliderItem: SliderItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
//  fun bind(item: SliderItem) {
    fun bind(item: SliderItem, size: Int) {
        this.sliderItem = item

        itemBinding.image.setImageResource(sliderItem.image)
        itemBinding.title.text = sliderItem.title
        itemBinding.contents.text = sliderItem.contents

        itemBinding.pageNumber.text = (adapterPosition + 1).toString()
        itemBinding.pageSum.text = " / $size"

        itemBinding.title.setOnClickListener {
            listener.onClickedTitle(sliderItem.title)
        }
    }

    override fun onClick(v: View?) {
        listener.onClicked(sliderItem)
    }
}



