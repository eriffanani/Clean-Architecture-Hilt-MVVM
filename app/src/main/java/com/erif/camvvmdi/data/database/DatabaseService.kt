package com.erif.camvvmdi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erif.camvvmdi.data.database.notes.NoteDao
import com.erif.camvvmdi.data.database.notes.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService: RoomDatabase() {

    companion object {
        private const val DB_NAME = "note.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun noteDao(): NoteDao

}