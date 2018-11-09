package top.yisen614.databinding.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.database.WordRoomDatabase
import top.yisen614.databinding.entity.Word


class WordRepository(application: Application) {

    var mWordDao: WordDao
    var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao).execute(word)
    }

    companion object {
        class InsertAsyncTask(dao: WordDao) : AsyncTask<Word, Void, Void>() {

            var mAsyncTaskDao: WordDao = dao

            override fun doInBackground(vararg params: Word?): Void? {
                mAsyncTaskDao.insert(params[0]!!)
                return null
            }
        }
    }
}