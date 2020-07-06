package top.yisen614.databinding.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import top.yisen614.databinding.persistence.entity.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
}