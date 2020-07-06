package top.yisen614.databinding.persistence.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import top.yisen614.databinding.persistence.dao.UserDao
import top.yisen614.databinding.persistence.dao.WordDao
import top.yisen614.databinding.persistence.entity.User
import top.yisen614.databinding.persistence.entity.Word

@Database(entities = [Word::class, User::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        var INSTANCE: MyDatabase? = null

        private val sRoomDatabase = object : RoomDatabase.Callback() {
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
        private val wordDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Word?): Void? {
            val word = Word()
            word.mWord = "word"
            wordDao.insert(word)
            return null
        }
    }
}