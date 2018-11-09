package top.yisen614.databinding.persistence.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import top.yisen614.databinding.persistence.dao.WordDao
import top.yisen614.databinding.persistence.database.MyDatabase
import top.yisen614.databinding.persistence.entity.Word


class WordRepository(application: Application) {

    var mWordDao: WordDao
    var mAllWords: LiveData<List<Word>>

    init {
        // 通过实例方法在此创建数据库实例
        val db = MyDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun deleteAll() {
        DataAsyncTask(mWordDao).execute(null, 2)
    }

    fun insert(word: Word) {
        DataAsyncTask(mWordDao).execute(word, 1)
    }

    companion object {
        /**
         * 线程任务, 可以执行多种方法
         */
        class DataAsyncTask(dao: WordDao) : AsyncTask<Any, Void, Void>() {

            var mAsyncTaskDao: WordDao = dao

            override fun doInBackground(vararg params: Any?): Void? {
                when (params[1]) {
                    1 -> mAsyncTaskDao.insert((params[0] as Word?)!!)
                    2 -> mAsyncTaskDao.deleteAll()
                }
                return null
            }
        }
    }
}