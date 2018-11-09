package top.yisen614.databinding.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.database.MyDatabase
import top.yisen614.databinding.entity.Word


class WordRepository(application: Application) {

    var mWordDao: WordDao
    var mAllWords: LiveData<List<Word>>

    init {
        val db = MyDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun insert(word: Word) {
        DataAsyncTask(mWordDao).execute(word, 1)
    }

    companion object {
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