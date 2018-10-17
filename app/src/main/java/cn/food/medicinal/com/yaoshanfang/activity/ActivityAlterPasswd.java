package cn.food.medicinal.com.yaoshanfang.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.food.medicinal.com.yaoshanfang.R;

/**
 * 修改密码Activity
 */
public class ActivityAlterPasswd extends AppCompatActivity {
    private LinearLayout layBack;
    private Button btnGetCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterpasswd);
        initView();
        initViewListener();
    }

    //初始化View
    private void initView(){
        layBack = findViewById(R.id.activity_alterpasswd_lay_back);
        btnGetCode = findViewById(R.id.activity_alterpasswd_btn_getyzcode);
    }

    //初始化View监听
    private void initViewListener(){
        layBack.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                view.setAlpha(0.6f);
            if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP)
                view.setAlpha(1);
            if (event.getAction() == MotionEvent.ACTION_UP)
                finish();
            return true;
        });

        btnGetCode.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnGetCode.setTextColor(getResources().getColor(R.color.colorEditBorderGray));
                btnGetCode.setBackgroundResource(R.drawable.style_edit_grayborder);
            }
            if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
                btnGetCode.setTextColor(getResources().getColor(R.color.colorEditBorderBlue));
                btnGetCode.setBackgroundResource(R.drawable.style_edit_bluebroder);
            }
            return true;
        });
    }
}
