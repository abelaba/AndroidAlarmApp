package com.example.stopwatch.fragment

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stopwatch.R
import kotlinx.android.synthetic.main.fragment_stop_watch.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopWatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StopWatchFragment : Fragment() {
    var stoptime:Long=0
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_watch, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        start_button.setOnClickListener{
            simpleChronometer.base= SystemClock.elapsedRealtime()+stoptime
            simpleChronometer.start()
            start_button.visibility= View.GONE
            pause_button.visibility=View.VISIBLE
        }
        pause_button.setOnClickListener{
            stoptime=simpleChronometer.base- SystemClock.elapsedRealtime()
            simpleChronometer.stop()
            pause_button.visibility=View.GONE
            start_button.visibility=View.VISIBLE
        }
        reset_button.setOnClickListener{
            simpleChronometer.base= SystemClock.elapsedRealtime()
            stoptime=0
            simpleChronometer.stop()
            pause_button.visibility=View.GONE
            start_button.visibility=View.VISIBLE
        }
        }


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StopWatchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String)=StopWatchFragment().apply {


            }
    }

}