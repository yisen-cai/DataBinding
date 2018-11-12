package top.yisen614.databinding.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import top.yisen614.databinding.R
import top.yisen614.databinding.persistence.entity.Word

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    val inflater = LayoutInflater.from(context)
    var wordItem: List<Word> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wordItem.size
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        val text = wordItem[p1].mWord
        p0.setText(text)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun setText(text: String) {
            wordItemView.text = text
        }
    }
}