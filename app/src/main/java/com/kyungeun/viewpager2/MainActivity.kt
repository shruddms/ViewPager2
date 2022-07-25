package com.kyungeun.viewpager2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.kyungeun.viewpager2.databinding.ActivityMainBinding
import timber.log.Timber


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

    private fun setSliderItems() {
        sliderItems = ArrayList()
        sliderItems.add(SliderItem(R.drawable.sample_1,"delicious meal 1","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_2,"delicious meal 2","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_3,"delicious meal 3","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_4,"delicious meal 4","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_5,"delicious meal 5","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_6,"delicious meal 6","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_7,"delicious meal 7","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_8,"delicious meal 8","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_9,"delicious meal 9","This is a sample about a delicious meal"))
        sliderItems.add(SliderItem(R.drawable.sample_10,"delicious meal 10","This is a sample about a delicious meal"))
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

    override fun onClickedImage(image: Int) {

    }
}