package com.kyungeun.viewpager2

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.kyungeun.viewpager2.databinding.ActivityMainBinding
import com.makeramen.roundedimageview.RoundedImageView

class MainActivity : AppCompatActivity(), SliderAdpater.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sliderItems: ArrayList<SliderItem>
    private lateinit var adapter: SliderAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSliderItems()
        setViewPager()
    }

    //set demo data
    private fun setSliderItems() {
        sliderItems = ArrayList()
        sliderItems.add(SliderItem(R.drawable.sample_1,"Delicious Meal 1","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_2,"Delicious Meal 2","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_3,"Delicious Meal 3","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_4,"Delicious Meal 4","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_5,"Delicious Meal 5","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_6,"Delicious Meal 6","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_7,"Delicious Meal 7","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_8,"Delicious Meal 8","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_9,"Delicious Meal 9","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_10,"Delicious Meal 10","This is a sample about a delicious meal"))
    }

    private fun setViewPager() {
        adapter = SliderAdpater(sliderItems,this)
        binding.viewPager.adapter = adapter

        binding.viewPager.clipToPadding = false;
        binding.viewPager.clipChildren = false;
        binding.viewPager.offscreenPageLimit = 3;
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER;

        val compositePageTransformer : CompositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer( MarginPageTransformer(25));
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - Math.abs(position)
            page.scaleY = 0.90f + r * 0.10f;
        }
        binding.viewPager.setPageTransformer(compositePageTransformer);
    }

    //viewpager2 item click
    override fun onClicked(item: SliderItem) {
        dialogImage(item.image)
    }

    //viewpager2 item title click
    override fun onClickedTitle(title: String) {
        Toast.makeText(this.applicationContext, title, Toast.LENGTH_SHORT).show()
    }

    private fun dialogImage(image: Int) {
        val builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.dialog_image, null)
        builder.setView(view)
        val dialog = builder.create()
        val imageView = view.findViewById<View>(R.id.image) as RoundedImageView
        imageView.setImageResource(image)

        imageView.setOnClickListener {
            dialog.dismiss()
        }

        if(dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.show()
    }
}