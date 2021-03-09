package com.example.stopwatch


import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*


class setalarm : AppCompatActivity() {

    // array of request codes

    var request = arrayListOf<Int>()
    var z = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setalarm)
        actionBar?.hide()
        supportActionBar?.hide()

        val doneButton = findViewById<Button>(R.id.doneButton)   // done button

        val closeButton = findViewById<ImageView>(R.id.close_button)   //
        val timePicker = findViewById<TimePicker>(R.id.datePicker1)

        val chipgroup = findViewById<ChipGroup>(R.id.chipgroup)
        var mHour = 0
        var mMinute = 0
        val date = Date()

        var am: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(applicationContext, broadcast::class.java)
        val intentmain = Intent(this, MainActivity::class.java)
        timePicker.is24HourView

        timePicker.setOnTimeChangedListener() { _, hourOfDay, minute ->
            mHour = hourOfDay
            mMinute = minute


        }

//        var helper = database(applicationContext)
//        var db = helper.readableDatabase
//



        doneButton.setOnClickListener{
            val cal_alarm = Calendar.getInstance()
            val cal_now = Calendar.getInstance()
            cal_now.setTime(date)
            cal_alarm.setTime(date)
            cal_alarm.set(Calendar.HOUR_OF_DAY, mHour)
            cal_alarm.set(Calendar.MINUTE, mMinute)
            cal_alarm.set(Calendar.SECOND, 0)

            Log.i("cal_alarm", cal_alarm.time.toString())
            val ids: List<Int> = chipgroup.getCheckedChipIds()
            request.clear()
            val checkList = arrayListOf<Int>()
            for (id in ids) {
                val chip: Chip = chipgroup.findViewById(id)
                checkList.add(chip.getTag().toString().toInt())
            }
            //requestcodearray = checkList
            request = checkList
            Log.i("requestcode", request.toString())

            if(checkList.size==0){
                if (cal_alarm.before(cal_now)) {
                    cal_alarm.add(Calendar.DATE, 1)


                }

                var pi  = PendingIntent.getBroadcast(applicationContext, 200, intent, FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pi)

            }else{
                for(i in checkList.indices){
                    if (cal_alarm.before(cal_now)) {
                        cal_alarm.add(Calendar.DATE, 7)  // add 7 to day if date before alarm
                        Log.i("works", cal_alarm.time.toString())


                    }
                    cal_alarm.set(Calendar.DAY_OF_WEEK, checkList[i])    //set day of week to i
                    Log.i("i", cal_alarm.time.toString())
                    var pi  = PendingIntent.getBroadcast(applicationContext, 1 * checkList[i], intent, 0)
                    am.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pi)   //set alarm
                    var x = cal_alarm.get(Calendar.DAY_OF_WEEK)

                    // Log.i("ids",x.toString())
                    // Log.i("cal_alarm", cal_alarm.time.toString())
                    var cv = ContentValues()
                    cv.put("HOUR", mHour)
                    cv.put("MINUTE", mMinute)
                    cv.put("DAYS", checkList.toString())
                  //  db.insert("ALARMS", null, cv)
                }


            }
            startActivity(intentmain)




        }

        closeButton.setOnClickListener{
            var z = savedInstanceState?.getIntegerArrayList("array")
            Log.i("zzzz",z.toString())

            if (z != null) {

                if(z.count()==0){
                    var pi  = PendingIntent.getBroadcast(applicationContext, 200, intent, FLAG_UPDATE_CURRENT)
                    am.cancel(pi)
                    Log.i("requestcode", request.toString())



                }else{
                    for(i in z.indices){
                        var pi  = PendingIntent.getBroadcast(applicationContext, 200 * z[i], intent, FLAG_UPDATE_CURRENT)
                        am.cancel(pi)
                        Log.i("requestcode", request.toString())

                    }

                }
            }



            startActivity(intentmain)

//
        }




    }
    @Override
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putIntegerArrayList("array",request)
        // etc.
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }



}
