package com.wjjang.stopwatch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wjjang.stopwatch.R
import com.wjjang.stopwatch.databinding.FragmentWatchBinding
import java.time.LocalTime
import java.time.ZoneId
import java.util.Timer
import kotlin.concurrent.timer

class WatchFragment : Fragment(R.layout.fragment_watch) {

    private var _binding : FragmentWatchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchBinding.inflate(inflater, container, false)


        var timerTask: Timer? = null
        val numberA = binding.numberA

            timerTask = timer(period = 1000) {
                val currentTime = LocalTime.now(ZoneId.of("Asia/Seoul"))
                val hour = currentTime.hour
                val minute = currentTime.minute
                val second = currentTime.second
                numberA.text = String.format("%02d:%02d:%02d",hour, minute, second)
            }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}