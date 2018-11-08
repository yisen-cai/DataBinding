package top.yisen614.databinding.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_word")
class Word(word:String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var mId: Int = 0

    @NonNull
    @ColumnInfo(name = "word")
    @NotNull
    var mWord: String = ""

    init {
        mWord = word
    }
}
