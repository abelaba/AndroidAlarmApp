
package com.example.stopwatch

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class database(context: Context):SQLiteOpenHelper(context,"ALARMDB",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ALARMS(_id INTEGER PRIMARY KEY AUTOINCREMENT,HOUR INT,MINUTE INT,DAYS STRING)")
        db?.execSQL("INSERT INTO ALARMS(HOUR,MINUTE,DAYS) VALUES(1,5,'[1,2,3]')")
        db?.execSQL("INSERT INTO ALARMS(HOUR,MINUTE,DAYS) VALUES(1,5,'[1,2,3]')")
        db?.execSQL("INSERT INTO ALARMS(HOUR,MINUTE,DAYS) VALUES(1,5,'[1,2,3]')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}