package top.yisen614.databinding.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.database.WordRoomDatabase
import top.yisen614.databinding.entity.Word


class WordRepository(application: Application) {
    lateinit var mWordDao: WordDao
    lateinit var mAllWords: LiveData<List<Word>>

    init {
        var db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    companion object {
        class insertAsyncTask(dao: WordDao) : AsyncTask<Word, Void, Void>() {

            lateinit var mAsyncTaskDao: WordDao

            init {
                mAsyncTaskDao = dao
            }

            override fun doInBackground(vararg params: Word?): Void? {
                mAsyncTaskDao.insert(params.get(0)!!)
                return null
            }
        }
    }
}