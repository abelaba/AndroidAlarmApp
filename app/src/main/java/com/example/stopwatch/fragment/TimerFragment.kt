package com.example.stopwatch.fragment

import android.R.attr.data
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stopwatch.R
import kotlinx.android.synthetic.main.fragment_stop_watch.*
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.*


// fragment initialization parameters
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TimerFragment : Fragment() {
    val startTimeInMillis: Long = 0
    lateinit var tvCountDownC: TextView
    lateinit var btnStartPause: Button
    lateinit var btnReset: Button
    lateinit var btnSetTimer: Button
    lateinit var pickerML: NumberPicker
    lateinit var pickerMR: NumberPicker
    lateinit var pickerSL: NumberPicker
    lateinit var pickerSR: NumberPicker
    lateinit var cdTimer: CountDownTimer
    var running: Boolean = false
    var setNew: Boolean = false
    var moveDir: Boolean = false
    var movingNext: Boolean = false
    var timeLeftInMillis: Long = 0
    var timeLeftInMillisL: Long = startTimeInMillis
    var timeLeftInMillisR: Long = 0
    var intervals: MutableList<Long> = mutableListOf(0)
    var currentCounter: Int = 1 //counters will be +1
    var minutes: Int = 0
    var seconds: Int = 0
    var timeLeftFormatted: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            pickerML = view.findViewById<NumberPicker>(R.id.np_minL)
            pickerMR = view.findViewById<NumberPicker>(R.id.np_minR)
            pickerSL = view.findViewById<NumberPicker>(R.id.np_secL)
            pickerSR = view.findViewById<NumberPicker>(R.id.np_secR)
            val pickers: List<NumberPicker> = listOf(pickerSR, pickerSL, pickerMR, pickerML)
            setVals(pickers)
            tvCountDownC = view.findViewById(R.id.tv_timerM)
            btnStartPause = view.findViewById(R.id.btn_start)
            btnReset = view.findViewById(R.id.btn_resetCur)
            btnSetTimer = view.findViewById(R.id.btn_setTime)

            btnStartPause.setOnClickListener {
                if (running) {
                    pauseTimer()
                }

                else{
                    startTimer()
                    Log.i("aasdasd", "asdas")
                }
            }

            btnReset.setOnClickListener {
                resetTimer()
                btnSetTimer.visibility= View.VISIBLE
                btnReset.visibility=View.GONE
                setVals(pickers)
            }

            btnSetTimer.setOnClickListener {
                setTimer()
                btnSetTimer.visibility= View.GONE
                btnReset.visibility=View.VISIBLE
            }

         updateCountDownText()
        }

    fun startTimer() {
        cdTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                if (currentCounter == intervals.size){
                    Toast.makeText(
                        context, "Countdown has finished",
                        Toast.LENGTH_SHORT
                    ).show()


                    running = false
                    setNew = true
                    updateCountDownText()
                    btnStartPause.text = "Start"
                    tvCountDownC.text ="00:00"
                } else {
                    movingNext = true
                    startTimer()
                }
            }
        }.start()

        running = true
        btnStartPause.text = "pause"
    }

    fun pauseTimer() {
        cdTimer.cancel()
        running = false
        btnStartPause.text = "Start"
    }

    fun resetTimer() {
        timeLeftInMillis = startTimeInMillis
        updateCountDownText()
        intervals[currentCounter - 1] = 0
    }

    fun updateCountDownText() {
        if (setNew) {
            timeLeftInMillis = intervals[currentCounter - 1]
            if (moveDir){
                if (intervals.size > currentCounter) {
                    timeLeftInMillisR = intervals[currentCounter]
                }
                if (currentCounter != 1) {
                    timeLeftInMillisL = intervals[currentCounter - 2]
                }
            }
            setNew = false
        }
        minutes = ((timeLeftInMillis / 1000) / 60).toInt()
        seconds = ((timeLeftInMillis / 1000) % 60).toInt()

        // correct placement
        timeLeftFormatted = "%02d:%02d".format(minutes, seconds, Locale.getDefault())
        tvCountDownC.text = timeLeftFormatted

        if (moveDir){
            minutes = ((timeLeftInMillisL / 1000) / 60).toInt()
            seconds = ((timeLeftInMillisL / 1000) % 60).toInt()

            timeLeftFormatted = "%02d:%02d".format(minutes, seconds, Locale.getDefault())

            minutes = ((timeLeftInMillisR / 1000) / 60).toInt()
            seconds = ((timeLeftInMillisR / 1000) % 60).toInt()

            timeLeftFormatted = "%02d:%02d".format(minutes, seconds, Locale.getDefault())

            timeLeftInMillisL = 0
            timeLeftInMillisR = 0
            moveDir = false
        }
    }

    // setting pickers from 0 to 9
    fun setVals(pickers: List<NumberPicker>) {
        for (x in pickers) {
            if (x == pickers.elementAt(2)) {
                x.minValue = 0
                x.maxValue = 9
                x.value = 0
            } else {
                x.minValue = 0
                x.maxValue = 9
                x.value = 0
            }
        }
    }

    fun setTimer() {
        if (running == false) {
            var x: Long = 0

            x += pickerML.value * 600000
            x += pickerMR.value * 60000
            x += pickerSL.value * 10000
            x += pickerSR.value * 1000

            intervals[currentCounter - 1] = x
            setNew = true
            updateCountDownText()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}