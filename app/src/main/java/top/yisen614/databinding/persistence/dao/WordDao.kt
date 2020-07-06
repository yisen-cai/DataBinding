package top.yisen614.databinding.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import top.yisen614.databinding.persistence.entity.Word

/**
 * 在此中加入相应查询方法
 */
@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM t_word")
    fun deleteAll()

    @Query("SELECT * from t_word ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}