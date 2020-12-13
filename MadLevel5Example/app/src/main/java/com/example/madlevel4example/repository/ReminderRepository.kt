package com.example.madlevel4example.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel2example.Reminder
import com.example.madlevel4example.dao.ReminderDao
import com.example.madlevel4example.database.ReminderRoomDatabase

public class ReminderRepository(context: Context) {

    private var reminderDao: ReminderDao

    init {
        val reminderRoomDatabase = ReminderRoomDatabase.getDatabase(context)
        reminderDao = reminderRoomDatabase!!.reminderDao()
    }

    fun getAllReminders() : LiveData<List<Reminder>> {
        return reminderDao?.getAllReminders() ?: MutableLiveData(emptyList())
    }

    suspend fun insertReminder(reminder: Reminder) {
        reminderDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminder) {
        reminderDao.updateReminder(reminder)
    }
}
