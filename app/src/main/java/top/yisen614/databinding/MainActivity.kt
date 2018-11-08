package top.yisen614.databinding

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import top.yisen614.databinding.dao.WordDao
import top.yisen614.databinding.database.WordRoomDatabase
import top.yisen614.databinding.entity.Word
import android.text.TextUtils
import android.content.Intent
import android.R.attr.button
import android.app.Activity


class MainActivity : AppCompatActivity() {

    lateinit var add: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val words = ArrayList<Word>()
        adapter.mWords = words
        add = findViewById(R.id.add)
//        add.setOnClickListener {
//            val replyIntent = Intent()
//            if (TextUtils.isEmpty(mEditWordView.getText())) {
//                setResult(Activity.RESULT_CANCELED, replyIntent)
//            } else {
//                val word = mEditWordView.getText().toString()
//                replyIntent.putExtra(EXTRA_REPLY, word)
//                setResult(Activity.RESULT_OK, replyIntent)
//            }
//            finish()
//        }
    }

    class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
        private val mInflater: LayoutInflater = LayoutInflater.from(context)
        lateinit var mWords: List<Word>

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
            val itemView = mInflater.inflate(R.layout.recyclerview_item, p0, false)
            return WordViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return mWords.size
        }

        override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
            val current = mWords[p1]
            p0.setText(current.mWord)
        }

        class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val wordItemView: TextView = itemView.findViewById(R.id.textView)

            fun setText(text: String) {
                wordItemView.text = text
            }
        }
    }

    companion object {

        var sRoomDataBaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE).execute();
            }
        }

        class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

            val mDao: WordDao = db.wordDao()

            override fun doInBackground(vararg params: Void?): Void? {
                mDao.deleteAll()
                var word = Word("hello")
                mDao.insert(word)
                word = Word("world")
                mDao.insert(word)
                return null
            }
        }
    }
}
