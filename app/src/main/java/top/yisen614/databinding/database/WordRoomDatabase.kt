package top.yisen614.databinding.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.entity.Word


@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        var INSTANCE: WordRoomDatabase? = null

        val sRoomDatabase = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    if (INSTANCE == null) {
                        // Create database here
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordRoomDatabase::class.java, "table_word"
                        ).fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabase)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }


    class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Word, Void, Void>() {
        val wordDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Word?): Void? {
            val word = Word("word")
            wordDao.insert(word)
            return null
        }
    }
}