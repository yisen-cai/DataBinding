package top.yisen614.databinding.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.database.MyDatabase
import top.yisen614.databinding.entity.Word
import top.yisen614.databinding.util.LiveDataTestUtil

@RunWith(AndroidJUnit4::class)
class WordRepositoryTest {

    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mWordDao: WordDao
    lateinit var mDb: MyDatabase


    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getTargetContext()

        mDb = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java!!)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()

        mWordDao = mDb.wordDao()
    }


    @After
    fun tearDown() {
        mDb.close()
    }


    @Test
    fun insert() {
        val word = Word("Hello ")
        val word1 = Word("Word")
        mWordDao.insert(word)
        mWordDao.insert(word1)
    }

    @Test
    fun getAll() {
        val words = LiveDataTestUtil.getValue(mWordDao.getAllWords())
        words.forEach { word ->
            print(word)
        }
    }
}