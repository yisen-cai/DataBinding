package top.yisen614.databinding

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import top.yisen614.databinding.adapter.WordListAdapter
import top.yisen614.databinding.viewModel.WordViewModel


class MainActivity : AppCompatActivity() {

    lateinit var add: FloatingActionButton
    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.mAllWords.observe(this, Observer { words ->
            adapter.wordItem = words!!
        })
    }
}
