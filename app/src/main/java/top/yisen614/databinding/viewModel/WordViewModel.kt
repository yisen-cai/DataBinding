package top.yisen614.databinding.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import top.yisen614.databinding.entity.Word
import top.yisen614.databinding.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    // 初始化创建repository
    var mRepository: WordRepository = WordRepository(application)
    var mAllWords: LiveData<List<Word>>

    init {
        mAllWords = mRepository.mAllWords
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }


}