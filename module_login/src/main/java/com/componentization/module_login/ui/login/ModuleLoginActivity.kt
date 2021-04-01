package com.componentization.module_login.ui.login

import android.componentization.base.Arouter.ArouterPath
import android.componentization.base.Arouter.MyNavigationCallback
import android.componentization.base.BaseActivity
import android.componentization.base.ConfigParam
import android.componentization.base.utils.mmkv.KVKEYS
import android.componentization.module_login.R
import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.huatu.htlx.utils.mmkv.KVUtils
import kotlinx.android.synthetic.main.activity_login.login
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.username
import kotlinx.android.synthetic.main.module_login_login_activity.*

@Route(path = ArouterPath.module_login)
class ModuleLoginActivity : BaseActivity() {

    @Autowired
    @JvmField
    var where_from : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.module_login_login_activity)
        tvFrom.text = where_from
        login.setOnClickListener {
            if (!TextUtils.isEmpty(password.text) && !TextUtils.isEmpty(username.text)){
                KVUtils.putBoolean(KVKEYS.isLogin,true)
                ARouter.getInstance().build(ArouterPath.module_mine_user_message)
                        .navigation(this, MyNavigationCallback(this))
            }
        }
    }
}