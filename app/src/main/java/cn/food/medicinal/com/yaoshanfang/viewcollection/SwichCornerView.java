package cn.food.medicinal.com.yaoshanfang.viewcollection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.food.medicinal.com.yaoshanfang.R;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class SwichCornerView extends View{
    private Bitmap switchBackground, switchThumb, switchOn, switchOff, currentSwitchType;
    private int width, height;
    private Paint paint;
    private boolean off = false, on = true;
    private boolean status = off;
    private float thumbOffsetLeft = 3, thumbOffsetTop = 0; //滑块当前偏移位置
    private boolean istouch = false;
    private float reglaxOffset = 3; //矫正滑块偏移
    public SwichCornerView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //初始化属性
        if(width <= 0 || height <= 0)
        {
            width = getMeasuredWidth();
            height = getMeasuredHeight();
            //Switch背景
            Bitmap temp = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_naozhong_bg);
            float scaleNum = height / 200.0f;
            switchBackground = Bitmap.createScaledBitmap(temp,width,height,true);
            temp.recycle();

            //Switch触摸滑块
            temp = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_naozhong_touch);
            switchThumb = Bitmap.createScaledBitmap(temp,height, height, true);
            temp.recycle();

            //Switch开启
            temp = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_naozhong_on);
            switchOn = Bitmap.createScaledBitmap(temp,height, height, true);
            temp.recycle();

            //Switch开启
            temp = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_naozhong_off);
            switchOff = Bitmap.createScaledBitmap(temp,height, height, true);
            temp.recycle();
            setChecked(status);
        }
        //如果是滑动，改变滑块颜色
        if (istouch)
            currentSwitchType = switchThumb;
        else if(status)
            currentSwitchType = switchOn;
        else
            currentSwitchType = switchOff;
        canvas.drawBitmap(switchBackground,0,0,paint);
        canvas.drawBitmap(currentSwitchType,thumbOffsetLeft,0,paint);

    }


    //拖拽滑块滑动
    private float touchDownX;
    private long downTime;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            istouch = true;
            touchDownX = event.getRawX();
            downTime = System.currentTimeMillis();
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            float dragDistance = event.getRawX() - touchDownX;
            thumbOffsetLeft += dragDistance;
            if(thumbOffsetLeft >= width - (height + reglaxOffset))
            {
                thumbOffsetLeft = width - (height + reglaxOffset);
            }
            if(thumbOffsetLeft < 0 + reglaxOffset)
                thumbOffsetLeft = reglaxOffset;
            invalidate();
            touchDownX = event.getRawX();
        }

        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
        {
            istouch = false;
            if(System.currentTimeMillis() - downTime < 160)
            {
                setChecked(!status);
                return true;
            }
            //判断开关当前位置
            if(thumbOffsetLeft <= ((width - height) / 2))
            {
                status = off;
                thumbOffsetLeft = reglaxOffset;

            }
            else
            {
                status = on;
                thumbOffsetLeft = width - (height + reglaxOffset);
            }
            invalidate();
        }
        return true;
    }

    //设置选择状态
    public void setChecked(boolean checked)
    {
        if(!checked)
            thumbOffsetLeft = reglaxOffset;
        else
            thumbOffsetLeft = width - (height + reglaxOffset);
        invalidate();
        status = checked;
    }

    //获取选择状态
    public boolean getChecked()
    {
        return status;
    }
}
