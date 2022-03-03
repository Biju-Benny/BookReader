
package com.example.bookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreader.Adapters.ViewPagerAdapter
import com.example.bookreader.Fragments.FavouritesFragment
import com.example.bookreader.Fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(FavouritesFragment(),"Favourites")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!
        tabs.getTabAt(1)!!


    }
}