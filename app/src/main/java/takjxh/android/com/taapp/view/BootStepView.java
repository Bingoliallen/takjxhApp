package takjxh.android.com.taapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import takjxh.android.com.taapp.R;


/**
 * 添加银行卡步骤下一步的自定义View
 */
public class BootStepView extends View {
    private int circularSize;//圆的半径
    private int circularColor;//圆的颜色
    private int circularTextSize;//圆内字的大小
    private int circularTextColor;//圆内字的颜色;
    private int lineSize;//横线的大小
    private int lineColor;//横线的颜色
    private int textSize;//文字的大小
    private int textColor;//文字的颜色
    private int defaultColor;//默认颜色
    private Paint paint;//画笔
    private boolean isTwoColor=false;
    private boolean isThreeColor=false;

    private String testOne="申请类型";
    private String testTwo="申请填写";
    private String testThree="申请完成";
    private int circular_num=3;

    public BootStepView(Context context) {
        super(context);
        init();
    }

    public BootStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public BootStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 加载自定义属性集合BootStepView
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BootStepView);
        // 解析集合中的属性属性
        // 将解析的属性传入到画圆的画笔颜色变量当中（本质上是自定义画圆画笔的颜色）
        // 第二个参数是默认设置颜色（即无指定情况下使用）
        circularColor = typedArray.getColor(R.styleable.BootStepView_circular_color, Color.RED);
        circularSize=typedArray.getDimensionPixelSize(R.styleable.BootStepView_circular_size,16);
        circularTextSize=typedArray.getDimensionPixelSize(R.styleable.BootStepView_circular_text_size,60);
        circularTextColor= typedArray.getColor(R.styleable.BootStepView_circular_text_color, Color.WHITE);
        lineSize=typedArray.getDimensionPixelSize(R.styleable.BootStepView_line_size,20);
        lineColor= typedArray.getColor(R.styleable.BootStepView_line_color, Color.BLACK);
        textSize=typedArray.getDimensionPixelSize(R.styleable.BootStepView_text_size,40);
        textColor= typedArray.getColor(R.styleable.BootStepView_text_color, Color.RED);
        defaultColor= typedArray.getColor(R.styleable.BootStepView_default_color, Color.BLACK);
        isTwoColor=typedArray.getBoolean(R.styleable.BootStepView_two_color,false);
        isThreeColor=typedArray.getBoolean(R.styleable.BootStepView_three_color,false);

        circular_num=typedArray.getInt(R.styleable.BootStepView_circular_num,2);

        testOne=typedArray.getString(R.styleable.BootStepView_one_text);
        testTwo=typedArray.getString(R.styleable.BootStepView_two_text);
        testThree=typedArray.getString(R.styleable.BootStepView_three_text);

        // 解析后释放资源
        typedArray.recycle();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BootStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width=480;
        int height=240;
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height);
        } else if (widthMode == MeasureSpec.AT_MOST ) {
            setMeasuredDimension(width, heightSize);
        }else if(heightMode== MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize, height);
        }
    }

    private void init(){
        // 创建画笔--圆 设置画笔属性
        paint=new Paint();
        paint.setColor(circularColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取传入的padding值
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width=getWidth();
        //开始绘制第一个圆
        int oneX=paddingLeft+circularSize*2;
        canvas.drawCircle(oneX,paddingTop,circularSize,paint);
        //开始绘制数字1
        paint.setColor(circularTextColor);
        paint.setTextSize(circularTextSize);
        canvas.drawText("1",oneX-circularTextSize/3,paddingTop+circularTextSize/3,paint);
        //开始绘制第一个线条
        paint.setColor(isTwoColor?circularColor:defaultColor);
        canvas.drawLine(oneX+circularSize,paddingTop,width/2-circularSize,paddingTop,paint);
        //开始绘制第一个文字
        paint.setColor(textColor);
        paint.setTextSize(textSize);

        float textWidth=paint.measureText(testOne);
        canvas.drawText(testOne,oneX-textWidth/2,paddingTop+circularSize*2+textSize/3,paint);

        //开始绘制第二个圆
        paint.setColor(isTwoColor?circularColor:defaultColor);
        int defaultStartX=width/2;
        canvas.drawCircle(defaultStartX,paddingTop,circularSize,paint);
        //开始绘制数字2
        paint.setColor(circularTextColor);
        paint.setTextSize(circularTextSize);
        canvas.drawText("2",defaultStartX-circularTextSize/3,paddingTop+circularTextSize/3,paint);
        if(circular_num>=3){
            //开始绘制第二个线条
            paint.setColor(isThreeColor?circularColor:defaultColor);
            canvas.drawLine(defaultStartX+circularSize,paddingTop,width-paddingRight-circularSize*3,paddingTop,paint);

        }
        //开始绘制第二个文字
        paint.setColor(textColor);
        paint.setTextSize(textSize);

        float testTwoWidth=paint.measureText(testTwo);
        canvas.drawText(testTwo,defaultStartX-testTwoWidth/2,paddingTop+circularSize*2+textSize/3,paint);

        if(circular_num>=3){
            //开始绘制第三个圆
            paint.setColor(isThreeColor?circularColor:defaultColor);
            int X=width-(paddingRight+circularSize*2);
            canvas.drawCircle(X,paddingTop,circularSize,paint);
            //开始绘制数字3
            paint.setColor(circularTextColor);
            paint.setTextSize(circularTextSize);
            canvas.drawText("3",X-circularTextSize/3,paddingTop+circularTextSize/3,paint);
            //开始绘制第二个文字
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            ;
            float testThreeWidth=paint.measureText(testThree);
            canvas.drawText(testThree,X-testThreeWidth/2,paddingTop+circularSize*2+textSize/3,paint);
        }

    }

    public void setTwoColor(boolean twoColor) {
        isTwoColor = twoColor;
        invalidate();
    }

    public void setThreeColor(boolean threeColor) {
        isThreeColor = threeColor;
        invalidate();
    }

    public void setTestOne(String testOne) {
        this.testOne = testOne;
        invalidate();
    }

    public void setTestTwo(String testTwo) {
        this.testTwo = testTwo;
        invalidate();
    }

    public void setTestThree(String testThree) {
        this.testThree = testThree;
        invalidate();
    }
}
