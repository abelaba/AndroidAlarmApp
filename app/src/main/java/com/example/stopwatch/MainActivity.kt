package com.example.stopwatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.stopwatch.fragment.HomeFragment
import com.example.stopwatch.fragment.StopWatchFragment
import com.example.stopwatch.fragment.TimerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val StopWatchFragment= StopWatchFragment()
        val TimerFragment = TimerFragment()
        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_baseline_access_alarms_24->makeCurrentFragment(homeFragment)
                R.id.ic_baseline_timer_24->makeCurrentFragment(StopWatchFragment)
                R.id.ic_baseline_timelapse_24->makeCurrentFragment(TimerFragment)
            }
            true
        }


    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
           replace(R.id.fl_wrapper, fragment)
            commit()
        }

}
