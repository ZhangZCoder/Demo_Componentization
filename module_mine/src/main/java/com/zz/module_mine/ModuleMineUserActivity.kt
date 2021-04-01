package com.zz.module_mine

import android.componentization.base.Arouter.ArouterPath
import android.componentization.base.BaseActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

@Route (path = ArouterPath.module_mine_user_message)
class ModuleMineUserActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.module_mine_activity_user)
    }
}