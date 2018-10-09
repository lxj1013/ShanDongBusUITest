package cn.com.hisistar.shandongbusuitest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * DATE 2018/10/8 14:30
 * AUTHOR lxj
 * 南无阿弥陀佛
 */
public class SDBusView extends View {

    private static final String TAG = "SDBusView";

    private Context mContext;

    private Paint mPaint;
    private Paint mLinePaint;
    private Paint mCirclePaint;
    private Paint mTextPaint;

    private RectF mLeftRectF;
    private RectF mRightRectF;
    private float mRectFWidth;

    private Path mLinePath;
    private Path mCirclePath;

    private float mViewWidth;
    private float mViewHeight;
    private float mMiddleHeight;
    private float mPaddingTop;
    private float mPaddingLeft;
    private float mPaddingRight;
    private float mTextSize;
    private float mCircleRadius;
    private float mLineLength;
    private float mLineWidth;

    private int flag = 0;

    private List<BusStation> mBusStationList;
    private List<Point> mCirclePointList;

    public SDBusView(Context context) {
        super(context);
    }

    public SDBusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public SDBusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.restore();
        cleanAll();
        drawCoordinate(canvas);

        drawLine(canvas);

        drawCircle(canvas, 29, 1);


//        canvas.drawRect(mLeftRectF,mPaint);

    }

    private void drawLine(Canvas canvas) {
        mLeftRectF.set(mPaddingLeft, mPaddingTop, mPaddingLeft + mRectFWidth, mViewHeight - mPaddingTop);
        mRightRectF.set(mViewWidth - mPaddingRight - mRectFWidth, mPaddingTop, mViewWidth - mPaddingRight, mViewHeight - mPaddingTop);

        mLinePath.addArc(mLeftRectF, -150, 60);
        mLinePath.arcTo(mRightRectF, -90, 180);
        mLinePath.arcTo(mLeftRectF, 90, 60);

        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mLineWidth);
        canvas.drawPath(mLinePath, mLinePaint);
    }

    private void drawCircle(Canvas canvas, int totalStation, int nowStation) {

        mLineLength = mViewWidth - mPaddingLeft - mPaddingRight - mRectFWidth;

        int lineUp = 0;
        int lineDown = 0;
        if (totalStation % 2 == 0) {
            lineUp = lineDown = totalStation / 2 - 1;
        } else {
            lineUp = totalStation / 2 - 1;
            lineDown = totalStation / 2;
        }


        mCirclePath.addCircle(mPaddingLeft, mMiddleHeight * 2 - mCircleRadius, mCircleRadius, Path.Direction.CW);

        float lineUpLength = (mLineLength - mCircleRadius * 2 * lineUp) / (lineUp + 1);
        float upStartCx = mPaddingLeft + mRectFWidth / 2.0f + lineUpLength + mCircleRadius;
        float upStartCy = mPaddingTop;
        for (int i = 0; i < lineUp; i++) {
            mCirclePath.addCircle(upStartCx + (lineUpLength + mCircleRadius * 2) * i, upStartCy, mCircleRadius, Path.Direction.CW);
        }

        float lineDownLength = (mLineLength - mCircleRadius * 2 * lineDown) / (lineDown + 1);
        float downStartCx = mViewWidth - mPaddingRight - mRectFWidth / 2.0f - lineDownLength - mCircleRadius;
        float downStartCy = mViewHeight - mPaddingTop;
        for (int i = 0; i < lineDown; i++) {
            mCirclePath.addCircle(downStartCx - (lineDownLength + mCircleRadius * 2) * i, downStartCy, mCircleRadius, Path.Direction.CW);
        }

        mCirclePath.addCircle(mPaddingLeft, mMiddleHeight * 3 + mCircleRadius, mCircleRadius, Path.Direction.CW);

        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mLineWidth);
        canvas.drawPath(mCirclePath, mCirclePaint);
        if (flag == 1) {
            mCirclePaint.setColor(Color.YELLOW);
            flag = 0;
        } else if (flag == 0) {
            mCirclePaint.setColor(Color.GREEN);
            flag = 1;
        }
        mCirclePaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mCirclePath, mCirclePaint);
        postInvalidateDelayed(1000);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mMiddleHeight = mViewHeight / 5.0f;
        mPaddingTop = mMiddleHeight;
        mPaddingLeft = 1920 / 64 * 3;
        mPaddingRight = 1920 / 64;
        Log.e(TAG, "onSizeChanged: " + "mViewWidth=" + mViewWidth + "  mViewHeight=" + mViewHeight);
    }

    /**
     * 得到字体高度，用于画Y轴字体与线居中
     *
     * @param paint
     * @return
     */
    protected float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    private void drawCoordinate(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, mViewHeight / 2, mViewWidth, mViewHeight / 2, mPaint);
        canvas.drawLine(mViewWidth / 2, 0, mViewWidth / 2, mViewHeight, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mCirclePaint = new Paint();
        mTextPaint = new Paint();

        mLeftRectF = new RectF();
        mRightRectF = new RectF();
        mRectFWidth = 100.0f;

        mLinePath = new Path();
        mCirclePath = new Path();

//        mPaddingTop = 5.0f;
//        mPaddingLeft = 10.0f;
//        mPaddingRight = 5.0f;
        mTextSize = 40.0f;
        mCircleRadius = 20.0f;
        mLineLength = 0;
        mLineWidth = mCircleRadius / 5.0f;

        mBusStationList = new ArrayList<>();
        mCirclePointList = new ArrayList<>();

    }

    private void cleanAll() {
        mPaint.reset();
        mLinePaint.reset();
        mCirclePaint.reset();
        mTextPaint.reset();

        mLinePath.reset();
        mCirclePath.reset();

        mBusStationList.clear();
        mCirclePointList.clear();

    }

}
