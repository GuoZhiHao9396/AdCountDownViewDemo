package com.gzh.adcountdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @ClassName: CountDownView
 * @Description: 描述类的作用
 * @author: GZH
 * @date: 2016/10/20 10:42
 */
public class CountDownView extends View {
    /**
     * 底盘默认颜色
     */
    private final int CANVASBASE_COLOR = 0x50555555;
    /**
     * 进度条默认颜色
     */
    private final int PROGRESS_COLOR = 0xFF6ADBFE;
    /**
     * 进度条默认宽度
     */
    private final int PROGRESS_SIZE = 5;
    /**
     * 文本内容
     */
    private final String TEXT_CONTENT = "跳过广告";
    /**
     * 文本字体默认大小
     */
    private final int TEXT_SIZE = 32;
    /**
     * 文本字体默认颜色
     */
    private final int TEXT_COLOR = 0xFFFFFFFF;
    /**
     * 倒计时间
     */
    private final int TIME_SIZE = 1000;
    private int canvabseColor;
    private int progressColor;
    private int progressSize;
    private int textSize;
    private int textColor;
    private int timeSize;
    private String textContent;
    //
    private Paint circlePaint;
    private Paint borderPaint;
    private TextPaint textPaint;
    //
    private float progress = 0;
    private StaticLayout staticLayout;
    private CountDownTimerListener listener;

    public CountDownView(Context context) {
        super(context);
        init();
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        //设置默认的底盘背景颜色
        canvabseColor = typedArray.getColor(R.styleable.CountDownView_gzh_canvasbase_color, CANVASBASE_COLOR);
        //设置进度条的颜色
        progressColor = typedArray.getColor(R.styleable.CountDownView_gzh_progress_color, PROGRESS_COLOR);
        //设置进度条的宽度
        progressSize = typedArray.getDimensionPixelSize(R.styleable.CountDownView_gzh_progress_size, PROGRESS_SIZE);
        //设置文本内容
        textContent = typedArray.getString(R.styleable.CountDownView_gzh_text_content);
        //设置文本大小
        textSize = typedArray.getDimensionPixelSize(R.styleable.CountDownView_gzh_text_size, TEXT_SIZE);
        //设置文本颜色
        textColor = typedArray.getColor(R.styleable.CountDownView_gzh_text_color, TEXT_COLOR);
        //设置倒计时间
        timeSize = typedArray.getInt(R.styleable.CountDownView_gzh_time_size, TIME_SIZE);
        //判断文本内容是否为空
        if (TextUtils.isEmpty(textContent)) {
            textContent = TEXT_CONTENT;
        }
        //重置
        typedArray.recycle();
        init();
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setDither(true);
        circlePaint.setColor(canvabseColor);
        circlePaint.setStyle(Paint.Style.FILL);
        //
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setColor(progressColor);
        borderPaint.setStrokeWidth(progressSize);
        borderPaint.setStyle(Paint.Style.STROKE);
        //
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        //
        int textWidth = (int) textPaint.measureText(textContent.substring(0, (textContent.length() + 1) / 2));
        staticLayout = new StaticLayout(textContent, textPaint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1F, 0, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            width = staticLayout.getWidth();
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            height = staticLayout.getHeight();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width, height);
        //画底盘
        canvas.drawCircle(width / 2, height / 2, min / 2, circlePaint);
        //画边框
        RectF rectF;
        if (width > height) {
            rectF = new RectF(width / 2 - min / 2 + progressSize / 2, 0 + progressSize / 2, width / 2 + min / 2 - progressSize / 2, height - progressSize / 2);
        } else {
            rectF = new RectF(progressSize / 2, height / 2 - min / 2 + progressSize / 2, width - progressSize / 2, height / 2 - progressSize / 2 + min / 2);
        }
        canvas.drawArc(rectF, -90, progress, false, borderPaint);
        //画居中的文字
        // canvas.drawText("稍等片刻", width / 2, height / 2 - textPaint.descent() + textPaint.getTextSize() / 2, textPaint);
        //画居中的文字
        canvas.translate(width / 2, height / 2 - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
    }

    public void start() {
        if (listener != null) {
            listener.onStartCount();
        }
        new CountDownTimer(timeSize, timeSize / 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                progress = (timeSize - millisUntilFinished) * 360 / timeSize;
                invalidate();
            }

            @Override
            public void onFinish() {
                Log.i("TAG", "onFinish: ");
                progress = 360;
                invalidate();
                if (listener != null) {
                    listener.onFinishCount();
                }
            }
        }.start();
    }

    public void stop() {
        listener = null;
    }

    public void setCountDownTimerListener(CountDownTimerListener listener) {
        this.listener = listener;
    }

    public interface CountDownTimerListener {

        void onStartCount();

        void onFinishCount();
    }
}
