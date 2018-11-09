package top.yisen614.databinding.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.entity.User
import top.yisen614.databinding.entity.Word

@Database(entities = [Word::class, User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        var INSTANCE: MyDatabase? = null

        val sRoomDatabase = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }


        fun getDatabase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    if (INSTANCE == null) {
                        // 此处创建数据库实例, 名称为database的Sqlite文件
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java, "database"
                        ).fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabase)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }


    class PopulateDbAsync(db: MyDatabase) : AsyncTask<Word, Void, Void>() {
        val wordDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Word?): Void? {
            val word = Word("word")
            wordDao.insert(word)
            return null
        }
    }
}