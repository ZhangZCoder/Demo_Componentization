package com.componentization.demo;


import android.componentization.base.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Bind({R.id.btGoto})
    Button btGoto;
    @Bind({R.id.btGotoJava})
    Button btGotoJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);
        btGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/loginmodule")
                        .navigation();
            }
        });

        btGotoJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
                ARouter.getInstance().build("/app/main")
                        .navigation();
            }
        });
    }
}
