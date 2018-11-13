package top.yisen614.databinding

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import top.yisen614.databinding.adapter.WordListAdapter
import top.yisen614.databinding.persistence.entity.Word
import top.yisen614.databinding.viewModel.WordViewModel


class MainActivity : AppCompatActivity() {

    lateinit var add: FloatingActionButton
    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = WordListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.mAllWords.observe(this, Observer { words ->
            adapter.wordItem = words!!
            adapter.notifyDataSetChanged()
        })
        recyclerView.adapter = adapter
    }

    fun addAction(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, WORD_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == WORD_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data!!.getStringExtra(WORD_KEY))
            wordViewModel.insert(word)
        } else {
            Toast.makeText(
                    this, getString(R.string.not_create),
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val WORD_CODE = 100
        const val WORD_KEY = "add_word_action"
    }

    fun openProduct(view: View) {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }

    fun dataBinding(view: View) {
        val intent = Intent(this, DataBindingActivity::class.java)
        startActivity(intent)
    }
}
