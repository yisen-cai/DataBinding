package top.yisen614.databinding.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import top.yisen614.databinding.entity.Word



@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM table_word")
    fun deleteAll()

    @Query("SELECT * from table_word ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}