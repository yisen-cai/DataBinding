package top.yisen614.databinding

import android.app.Activity
import android.app.PendingIntent
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import top.yisen614.databinding.adapter.WordListAdapter
import top.yisen614.databinding.notification.NotificationHelper
import top.yisen614.databinding.persistence.entity.Word
import top.yisen614.databinding.viewModel.WordViewModel

/**
 * created by 16539
 * 2019/11/25
 */
class MainActivity : AppCompatActivity() {

    lateinit var add: FloatingActionButton
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 创建channel
        NotificationHelper(this).createNotificationChannel()

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


    fun notiAction(view: View) {
        // 显式创建应用Activity的intent
        val intent = Intent(this, DataBindingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        // 创建通知
        val notification = NotificationCompat.Builder(this, getString(R.string.channel_name))
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // 设置通知所需的的intent, 将会在点击时触发
            .setContentIntent(pendingIntent)
            .setAutoCancel(true).build()

        // 发送通知
        NotificationManagerCompat.from(this).notify(0, notification)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == WORD_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word()
            word.mWord = data!!.getStringExtra(WORD_KEY)
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
