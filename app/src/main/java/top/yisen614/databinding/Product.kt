package top.yisen614.databinding

import android.databinding.BaseObservable
import android.databinding.Bindable


/**
 *
 * Created by 16539
 * 2018/11/13
 */
class Product(name: String, des: String) : BaseObservable() {

    @get:Bindable
    var name: String = name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var des: String = des
        set(value) {
            field = value
            notifyPropertyChanged(BR.des)
        }

}