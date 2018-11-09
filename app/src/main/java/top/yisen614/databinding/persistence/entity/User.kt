package top.yisen614.databinding.persistence.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "t_user")
class User(name: String, age: Int, gender: Int) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "username")
    @NotNull
    var name: String = name

    var age: Int = age

    var gender: Int = gender

}