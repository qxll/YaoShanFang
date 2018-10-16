package cn.food.medicinal.com.yaoshanfang.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import cn.food.medicinal.com.yaoshanfang.R;
import cn.food.medicinal.com.yaoshanfang.config.ApplicationDatapool;
import cn.food.medicinal.com.yaoshanfang.fragment.FragmentHome;
import cn.food.medicinal.com.yaoshanfang.fragment.FragmentLife;
import cn.food.medicinal.com.yaoshanfang.fragment.FragmentMe;
import cn.food.medicinal.com.yaoshanfang.fragment.FragmentShop;

public class ActivityFragmentManager extends AppCompatActivity {
    private LinearLayout layHome, layShop, layLife, layMe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentmanager);
//        initStatusBar();
//        initView();
//        initEvent();
//        initFragment();
    }

    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initView(){
        layHome = findViewById(R.id.activity_fragmentmanager_lay_home);
        layShop = findViewById(R.id.activity_fragmentmanager_lay_shop);
        layLife = findViewById(R.id.activity_fragmentmanager_lay_life);
        layMe = findViewById(R.id.activity_fragmentmanager_lay_me);
    }

    private void initEvent(){
        View.OnClickListener onClickListener = view->{
            switch (view.getId()){
                case R.id.activity_fragmentmanager_lay_home:
                    getFragmentManager().beginTransaction().replace(R.id.activity_fragmentmanager_framecontent, ApplicationDatapool.fragmentHome).commit();
                    break;
                case R.id.activity_fragmentmanager_lay_shop:
                    getFragmentManager().beginTransaction().replace(R.id.activity_fragmentmanager_framecontent, ApplicationDatapool.fragmentShop).commit();
                    break;
                case R.id.activity_fragmentmanager_lay_life:
                    getFragmentManager().beginTransaction().replace(R.id.activity_fragmentmanager_framecontent, ApplicationDatapool.fragmentLife).commit();
                    break;
                case R.id.activity_fragmentmanager_lay_me:
                    getFragmentManager().beginTransaction().replace(R.id.activity_fragmentmanager_framecontent, ApplicationDatapool.fragmentMe).commit();
                    break;
            }
        };
        layHome.setOnClickListener(onClickListener);
        layShop.setOnClickListener(onClickListener);
        layLife.setOnClickListener(onClickListener);
        layMe.setOnClickListener(onClickListener);
    }

    private void initFragment(){
        ApplicationDatapool.fragmentHome = new FragmentHome();
        ApplicationDatapool.fragmentLife = new FragmentLife();
        ApplicationDatapool.fragmentMe = new FragmentMe();
        ApplicationDatapool.fragmentShop = new FragmentShop();
        //打开Home
        getFragmentManager().beginTransaction().replace(R.id.activity_fragmentmanager_framecontent, ApplicationDatapool.fragmentHome).commit();
    }
}
