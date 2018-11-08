package top.yisen614.databinding.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import top.yisen614.databinding.entity.Word
import top.yisen614.databinding.repository.WordRepository

class WordViewModel(application: Application): AndroidViewModel(application) {
    var mRepository:WordRepository
    var mAllWords: LiveData<List<Word>>

    init {
        mRepository = WordRepository(application)
        mAllWords = mRepository.mAllWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}