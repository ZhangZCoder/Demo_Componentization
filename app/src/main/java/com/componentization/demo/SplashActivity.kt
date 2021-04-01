package com.componentization.demo

import android.componentization.base.Arouter.ArouterPath
import android.componentization.base.Arouter.MyNavigationCallback
import android.componentization.base.BaseActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.huatu.htlx.utils.mmkv.KVUtils

@Route(path = ArouterPath.module_app_splash)
class SplashActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        KVUtils.get()!!.init(this)
        setContentView(R.layout.app_activity_splash)
        val handler = Handler()
        handler.postDelayed(Runnable {
            ARouter.getInstance().build(ArouterPath.module_mine_user_message)
                    .navigation(this ,MyNavigationCallback(this))
            finish()
        }, 3000)
    }
}