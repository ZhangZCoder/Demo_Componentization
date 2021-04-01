package android.componentization.base.Arouter

import android.componentization.base.ConfigParam
import android.componentization.base.utils.mmkv.KVKEYS
import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.huatu.htlx.utils.mmkv.KVUtils

/**
 * Created by zz on 4/1/21.
 */
@Interceptor(priority = 1, name = "登陆拦截器")
class MyIInterceptor : IInterceptor {

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d("======", "MyIInterMyIInterceptorceptor===onLost" + postcard!!.path!!.toString())

        if (ConfigParam.NOINTERCEPTOR.equals(postcard!!.extras.getString(ConfigParam.NOINTERCEPTOR))) {
            callback!!.onContinue(postcard)
        } else {
            when (postcard.path) {
                ArouterPath.module_main_home, ArouterPath.module_login -> {
                    callback!!.onContinue(postcard)
                }
                ArouterPath.module_mine_user_message -> {
                    if (KVUtils.getBoolean(KVKEYS.isLogin)) {
                        callback!!.onContinue(postcard)
                    } else {
                        callback!!.onInterrupt(null)
                    }
                }

            }
        }
    }

    override fun init(context: Context?) {

    }
}