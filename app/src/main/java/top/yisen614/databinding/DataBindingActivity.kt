package top.yisen614.databinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import top.yisen614.databinding.databinding.ActivityBindingTestBinding
import top.yisen614.databinding.databinding.ActivityDataBindingBinding
import top.yisen614.databinding.persistence.entity.User

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 通过数据绑定工具类获取绑定实例
        var binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

        val binding1: ActivityBindingTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding_test)

        val user = User(1, "yisen614", 12, 1)

        // 设置绑定用户
        binding1.user = user

    }
}
