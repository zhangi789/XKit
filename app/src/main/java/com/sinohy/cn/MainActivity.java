package com.sinohy.cn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tool.cn.XKit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    XKit.setStatusBarTextColor(this,true,XKit.getColor(this,R.color.colorAccent));

     //   XKit.setFullScreen(this,false);
       XKit.setImmerse(this,false,Color.argb(23,123,34,56));
    }
}
