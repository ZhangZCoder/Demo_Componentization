package com.zz.module_main

import android.componentization.base.Arouter.ArouterPath
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

@Route (path = ArouterPath.module_main_home)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.module_main_activity_main)
    }
}