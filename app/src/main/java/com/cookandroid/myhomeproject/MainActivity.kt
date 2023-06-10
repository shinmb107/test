package com.cookandroid.myhomeproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.myhomeproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "시간표"
                1 -> tab.text = "일정추가"
                2 -> tab.text = "일정관리"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var pos: Int = tab?.position ?: 0
                when (pos) {
                    0 -> binding.viewPager.setCurrentItem(0)
                    1 -> binding.viewPager.setCurrentItem(1)
                    2 -> binding.viewPager.setCurrentItem(2)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}