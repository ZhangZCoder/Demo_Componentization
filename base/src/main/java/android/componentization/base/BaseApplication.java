package android.componentization.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.huatu.htlx.utils.mmkv.KVUtils;

/**
 * Created by zz on 2020/4/1.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance(){
        return instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null){
            instance = this;
        }
        initARouter();
        KVUtils.Companion.get().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initARouter() {
            ARouter.openLog();
            ARouter.openDebug();
        ARouter.init(this);
    }
}
