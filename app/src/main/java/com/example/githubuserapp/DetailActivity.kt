package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ActivityDetailBinding
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "DATA"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
//            R.string.tab_setting
        )
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_detail)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<User>(EXTRA_DATA) as User

        binding.tvDetailName.text = data.name
        binding.tvDetailUsername.text = data.username
        binding.tvDetailCompany.text = data.company
        binding.tvDetailLocation.text = data.location
        binding.tvDetailRepository.text = data.repository
        binding.tvDetailFollowers.text = data.followers
        binding.tvDetailFollowing.text = data.following

        Glide.with(this)
            .load(data.avatar)
            .circleCrop()
            .into(binding.imgDetailAvatar)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.appName = resources.getString(R.string.app_name)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}