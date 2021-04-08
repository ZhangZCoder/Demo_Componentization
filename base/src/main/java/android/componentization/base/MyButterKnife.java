package android.componentization.base;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zz on 4/7/21.
 */
public class MyButterKnife {
    public static void bind(AppCompatActivity activity){
        Class<? extends AppCompatActivity> aClass = activity.getClass();
        String canonicalName = aClass.getCanonicalName();
        try {
            Class<?> bindClass =  bindClass = aClass.getClassLoader().loadClass(canonicalName + "_ViewBinding");
            try {
                bindClass.getConstructor(aClass).newInstance(activity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("====","butterKnife" + canonicalName);
    }
}
