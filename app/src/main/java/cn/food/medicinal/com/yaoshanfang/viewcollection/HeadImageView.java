package cn.food.medicinal.com.yaoshanfang.viewcollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;



/**
 * 圆形头像
 * Created by Administrator on 2017/11/24 0024.
 */

@SuppressLint("AppCompatCustomView")
public class HeadImageView extends ImageView{
    private float radius = 80; //半径
    private int stroke_width = 3;
    private boolean needstroke = false;
    private int resource;
    private Bitmap bitmap;
    public HeadImageView(Context context)
    {
        super(context);
    }

    public HeadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_HARDWARE,null); //开启离线加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null); //关闭软件加速
        for (int index = 0; index < attrs.getAttributeCount(); index++)
        {
            String tag = attrs.getAttributeName(index);
            if(tag.contains("radius"))
                if (attrs.getAttributeValue(index).contains("dip"))
                    radius = 1 + (int) (Float.parseFloat(attrs.getAttributeValue(index).replace("dip","")) * getResources().getDisplayMetrics().density);
                else
                    radius = Float.parseFloat(attrs.getAttributeValue(index));
            if(tag.contains("background"))
                resource = attrs.getAttributeResourceValue(index,-1);
            if(tag.contains("needstroke") && attrs.getAttributeValue(index).equals("true"))
                needstroke = true;
            if(tag.contains("stroke_width"))
            {
                stroke_width = Integer.parseInt(attrs.getAttributeValue(index));
            }

        }
    }

    public void drawDst(Canvas canvas, Paint paint)
    {

//        Drawable imageDrawable = getResources().getDrawable(resource);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            imageDrawable.setBounds(imageDrawable.getDirtyBounds());wqa
//            Log.e("Drawable", imageDrawable.getIntrinsicWidth()+"");
//        }
//
        Bitmap mbitmap;
        if (bitmap == null)
            mbitmap = BitmapFactory.decodeResource(getResources(),resource);
        else
            mbitmap = bitmap;
        mbitmap = Bitmap.createScaledBitmap(mbitmap,(int)(radius * 2), (int)(radius * 2), true);
        if(needstroke)
            canvas.drawBitmap(mbitmap,stroke_width * 3 + 3, stroke_width * 3 + 3,paint);
        else
            canvas.drawBitmap(mbitmap,0,0,paint);
        //绘制边框

    }

    public void drawSrc(Canvas canvas, Paint paint)
    {
        Bitmap bitmap = Bitmap.createBitmap((int)(radius * 2), (int)(radius * 2), Bitmap.Config.ARGB_8888);
        Canvas cas = new Canvas(bitmap);
        cas.drawCircle(radius,radius,radius,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        if(needstroke)
            canvas.drawBitmap(bitmap,(stroke_width * 3 + 3), (stroke_width * 3 + 3),paint);
        else
            canvas.drawBitmap(bitmap,0,0,paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("Draw", "OnDraw");
        canvas.drawARGB(0xff, 0xff, 0xff, 0xff);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawDst(canvas,paint);
        drawSrc(canvas,paint);
        canvas.save();
        if(needstroke)
        {
            paint.reset();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(stroke_width);
            paint.setAntiAlias(true);
            canvas.drawCircle(radius + (stroke_width * 3) + 3, radius + (stroke_width * 3) + 3, radius + (stroke_width * 3), paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }
}
