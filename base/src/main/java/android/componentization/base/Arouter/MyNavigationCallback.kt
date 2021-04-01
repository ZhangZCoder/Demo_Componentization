package android.componentization.base.Arouter

import android.app.Activity
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by zz on 4/1/21.
 */
class MyNavigationCallback : NavigationCallback{
    lateinit var mActivity: Activity;
    constructor(mActivity: Activity){
        this.mActivity = mActivity
    }
    override fun onLost(postcard: Postcard?) {
        Log.d("======", "MyNavigationCallback===onLost")
    }

    override fun onFound(postcard: Postcard?) {
        Log.d("======", "MyNavigationCallback===onFound")
    }

    override fun onInterrupt(postcard: Postcard?) {
        Log.d("======", "MyNavigationCallback===onInterrupt")
        mActivity.runOnUiThread{
            ARouter.getInstance().build(ArouterPath.module_login)
                    .withString("where_from", postcard?.path)
                    .navigation()
        }
    }

    override fun onArrival(postcard: Postcard?) {
        Log.d("======", "MyNavigationCallback===onArrival")
//        TODO("Not yet implemented")
    }

}