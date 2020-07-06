package top.yisen614.databinding.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import top.yisen614.databinding.persistence.entity.Word
import top.yisen614.databinding.persistence.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    // 初始化创建repository
    private var mRepository: WordRepository = WordRepository(application)
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