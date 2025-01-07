package com.wjjang.stopwatch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wjjang.stopwatch.R
import com.wjjang.stopwatch.databinding.FragmentTimerBinding
import java.util.Timer
import kotlin.concurrent.timer

class TimerFragment : Fragment(R.layout.fragment_timer) {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)

        var isRunning = false
        var sec: Int = 0
        var min: Int = 0
        var milliSec: Int = 0
        val btn1 = binding.btn1
        val btn2 = binding.btn2
        val numberA = binding.numberA
        var timerTask: Timer? = null

        btn1.setOnClickListener {
            if (isRunning) {
                timerTask?.cancel()
                isRunning = false
                btn1.text = "시작"
                Toast.makeText(requireContext(), "타이머를 정지합니다.", Toast.LENGTH_SHORT).show()
            } else {
                isRunning = true
                timerTask = timer(period = 10) {
                    milliSec++
                    if (milliSec >= 100) {
                        milliSec = 0
                        sec++
                        if(sec>=60){
                            sec = 0
                            min++
                        }
                    }
                    activity?.runOnUiThread {
                        if(min==0){
                            numberA.text = String.format("%d:%02d", sec, milliSec)
                        }else{
                            numberA.text = String.format("%d:%02d", min, sec)
                        }

                    }
                }
                btn1.text = "정지"
                Toast.makeText(requireContext(), "타이머를 시작합니다.", Toast.LENGTH_SHORT).show()
            }
            btn2.isEnabled = !isRunning
        }

        btn2.setOnClickListener {
            sec = 0
            milliSec = 0
            numberA.text = String.format("%d:%02d", sec, milliSec)
            isRunning = false
            timerTask?.cancel()
            Toast.makeText(requireContext(), "타이머를 초기화합니다.", Toast.LENGTH_SHORT).show()
            btn1.isEnabled = true
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
