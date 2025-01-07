package com.wjjang.stopwatch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wjjang.stopwatch.fragments.HomeFragment
import com.wjjang.stopwatch.fragments.TimerFragment
import com.wjjang.stopwatch.fragments.WatchFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HomeFragment(),
        TimerFragment(),
        WatchFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
