package com.wjjang.stopwatch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.wjjang.stopwatch.adapter.ViewPagerAdapter
import com.wjjang.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val viewPagerAdapter = ViewPagerAdapter(this)
        mBinding.viewpager.adapter = viewPagerAdapter

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(mBinding.myBottomNav, navController)

        mBinding.myBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> mBinding.viewpager.currentItem = 0
                R.id.timerFragment -> mBinding.viewpager.currentItem = 1
                R.id.watchFragment -> mBinding.viewpager.currentItem = 2
            }
            true
        }

        mBinding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val itemId = when (position) {
                    0 -> R.id.homeFragment
                    1 -> R.id.timerFragment
                    2 -> R.id.watchFragment
                    else -> R.id.homeFragment
                }
                mBinding.myBottomNav.selectedItemId = itemId
                navController.navigate(itemId)
            }
        })
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish() // 앱 종료
        } else {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
