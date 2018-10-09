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
    private static final int COLOR_GREEN = 0xff7ece53;
    private static final int COLOR_YELLOW = 0xffdddd00;
    private static final int COLOR_RED = 0xfffe0000;

    private Context mContext;

    private Paint mPaint;
    private Paint mLinePaint;
    private Paint mCirclePaint;
    private Paint mTextPaint;

    private RectF mLeftRectF;
    private RectF mRightRectF;
    private float mRectFWidth;

    private Path mLinePath;
    private Path mCirclePath1;
    private Path mCirclePath2;

    private float mViewWidth;
    private float mViewHeight;
    private float mMiddleHeight;
    private float mPaddingTop;
    private float mPaddingLeft;
    private float mPaddingRight;
    private float mTextSize;
    private float mTextPadding;
    private float mCircleRadius;
    private float mLineLength;
    private float mLineWidth;

    private int flag = 0;
    private int nowStation = 1;
    private boolean isInStop = false;
    private boolean isOutStop = false;

    private List<BusStation> mBusStationList;
    private List<Point> mCirclePointList;
    private List<Point> mTextPointList;
    private Point mPoint;

    public SDBusView(Context context) {
        super(context);
    }

    public SDBusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void initStations() {
        mBusStationList.add(new BusStation("公交交交南南站", false));
        mBusStationList.add(new BusStation("潢潼", false));
        mBusStationList.add(new BusStation("东河路兴业路", false));
        mBusStationList.add(new BusStation("俊知工业园", false));
        mBusStationList.add(new BusStation("恒峰电缆", false));
        mBusStationList.add(new BusStation("国际环保展示中", false));
        mBusStationList.add(new BusStation("一环集团", false));
        mBusStationList.add(new BusStation("江南和院", false));
        mBusStationList.add(new BusStation("西花园三村", false));
        mBusStationList.add(new BusStation("西花园", false));
        mBusStationList.add(new BusStation("丰泽苑西区", false));
        mBusStationList.add(new BusStation("武警医院", false));
        mBusStationList.add(new BusStation("环科园成人学校", false));
        mBusStationList.add(new BusStation("绿园新村", false));
        mBusStationList.add(new BusStation("宜兴日报社", false));
        mBusStationList.add(new BusStation("迎宾路", false));
        mBusStationList.add(new BusStation("老干部大学", false));
        mBusStationList.add(new BusStation("王府", false));
        mBusStationList.add(new BusStation("人民医院34", false));
        mBusStationList.add(new BusStation("沁园", false));
        mBusStationList.add(new BusStation("水浮桥", false));
        mBusStationList.add(new BusStation("解放广场", false));
        mBusStationList.add(new BusStation("东虹桥", false));
        mBusStationList.add(new BusStation("东虹新村", false));
        mBusStationList.add(new BusStation("东方明珠", false));
        mBusStationList.add(new BusStation("临溪花苑", false));
        mBusStationList.add(new BusStation("妇幼保健所", false));
        mBusStationList.add(new BusStation("东氿一号", false));
        mBusStationList.add(new BusStation("东氿广场", false));
        mBusStationList.add(new BusStation("苏宁天氿御城", false));
        mBusStationList.add(new BusStation(">>>", false));
        mBusStationList.add(new BusStation("城东公交公交总总站", false));
    }

    public SDBusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.restore();
        cleanAll();
        initStations();
        drawCoordinate(canvas);

        drawLine(canvas);

        drawCircle(canvas, mBusStationList.size(), nowStation);

        drawText(canvas);
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
        if (nowStation > totalStation) {
            nowStation = totalStation;
        }
        if (nowStation < 1) {
            nowStation = 1;
        }

        mLineLength = mViewWidth - mPaddingLeft - mPaddingRight - mRectFWidth;

        int lineUp = 0;
        int lineDown = 0;
        if (totalStation % 2 == 0) {
            lineUp = lineDown = totalStation / 2 - 1;
        } else {
            lineUp = totalStation / 2 - 1;
            lineDown = totalStation / 2;
        }


//        mCirclePath.addCircle(mPaddingLeft, mMiddleHeight * 2 - mCircleRadius, mCircleRadius, Path.Direction.CW);
        mCirclePointList.add(new Point(mPaddingLeft, mMiddleHeight * 2 - mCircleRadius * 2));

        float lineUpLength = (mLineLength - mCircleRadius * 2 * lineUp) / (lineUp + 1);
        float upStartCx = mPaddingLeft + mRectFWidth / 2.0f + lineUpLength + mCircleRadius;
        float upStartCy = mPaddingTop;
        for (int i = 0; i < lineUp; i++) {
//            mCirclePath.addCircle(upStartCx + (lineUpLength + mCircleRadius * 2) * i, upStartCy, mCircleRadius, Path.Direction.CW);
            mCirclePointList.add(new Point(upStartCx + (lineUpLength + mCircleRadius * 2) * i, upStartCy));
        }

        float lineDownLength = (mLineLength - mCircleRadius * 2 * lineDown) / (lineDown + 1);
        float downStartCx = mViewWidth - mPaddingRight - mRectFWidth / 2.0f - lineDownLength - mCircleRadius;
        float downStartCy = mViewHeight - mPaddingTop;
        for (int i = 0; i < lineDown; i++) {
//            mCirclePath.addCircle(downStartCx - (lineDownLength + mCircleRadius * 2) * i, downStartCy, mCircleRadius, Path.Direction.CW);
            mCirclePointList.add(new Point(downStartCx - (lineDownLength + mCircleRadius * 2) * i, downStartCy));
        }

//        mCirclePath.addCircle(mPaddingLeft, mMiddleHeight * 3 + mCircleRadius, mCircleRadius, Path.Direction.CW);
        mCirclePointList.add(new Point(mPaddingLeft, mMiddleHeight * 3 + mCircleRadius * 2));

        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mLineWidth);

        for (int i = 0; i < nowStation - 1; i++) {
            mPoint = mCirclePointList.get(i);
            mCirclePath1.addCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, Path.Direction.CW);
        }
        for (int i = nowStation; i < totalStation; i++) {
            mPoint = mCirclePointList.get(i);
            mCirclePath2.addCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, Path.Direction.CW);
        }

        mPoint = mCirclePointList.get(nowStation - 1);

        canvas.drawPath(mCirclePath1, mCirclePaint);
        canvas.drawCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, mCirclePaint);
        canvas.drawPath(mCirclePath2, mCirclePaint);

        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(COLOR_RED);
        canvas.drawPath(mCirclePath1, mCirclePaint);

        if (flag == 0) {
            mCirclePaint.setColor(COLOR_YELLOW);
            flag = 1;
        } else {
            mCirclePaint.setColor(COLOR_GREEN);
            flag = 0;
        }

        canvas.drawCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, mCirclePaint);
        mCirclePaint.setColor(COLOR_GREEN);
        canvas.drawPath(mCirclePath2, mCirclePaint);

        postInvalidateDelayed(1000);

    }


    private void drawText(Canvas canvas) {

        mTextPointList.addAll(mCirclePointList);

        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        float textHeight = getFontHeight(mTextPaint);
        float textWidth = 0f;
        float textWidth1 = 0f;
        float textWidth2 = 0f;
        String text = "";
        String text1 = "";
        String text2 = "";
//        canvas.drawText("一公交南站", 500, 300, mTextPaint);

        for (int i = 0; i < mCirclePointList.size(); i += 2) {
            mPoint = mCirclePointList.get(i);
            mTextPointList.set(i, new Point(mPoint.getX(), mPoint.getY() + mCircleRadius + textHeight));
        }
        for (int i = 1; i < mCirclePointList.size(); i += 2) {
            mPoint = mCirclePointList.get(i);
            mTextPointList.set(i, new Point(mPoint.getX(), mPoint.getY() - (mCircleRadius + mTextPadding)));
        }
        for (int i = 0; i < mTextPointList.size(); i++) {
            mPoint = mTextPointList.get(i);
            text = mBusStationList.get(i).getStationName();


            if (text.length() > 6) {
                text1 = text.substring(0, text.length() / 2);
                text2 = text.substring(text.length() / 2);
                textWidth1 = mTextPaint.measureText(text1);
                textWidth2 = mTextPaint.measureText(text2);

                if (i % 2 == 0) {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY(), mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY() + textHeight, mTextPaint);
                } else {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY() - textHeight, mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY(), mTextPaint);
                }
            } else {
                textWidth = mTextPaint.measureText(text);
                canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY(), mTextPaint);
            }
        }
        canvas.drawText("●●●",1000,300,mTextPaint);

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
        mCirclePath1 = new Path();
        mCirclePath2 = new Path();

//        mPaddingTop = 5.0f;
//        mPaddingLeft = 10.0f;
//        mPaddingRight = 5.0f;
        mTextSize = 20.0f;
        mTextPadding = 6.0f;
        mCircleRadius = 13.0f;
        mLineLength = 0;
        mLineWidth = mCircleRadius / 5.0f;

        mBusStationList = new ArrayList<>();
        mCirclePointList = new ArrayList<>();
        mTextPointList = new ArrayList<>();

    }

    private void cleanAll() {
        mPaint.reset();
        mLinePaint.reset();
        mCirclePaint.reset();
        mTextPaint.reset();

        mLinePath.reset();
        mCirclePath1.reset();
        mCirclePath2.reset();

        mBusStationList.clear();
        mCirclePointList.clear();
        mTextPointList.clear();

    }


    public void setBusStationList(List<BusStation> busStationList) {
        mBusStationList = busStationList;
        invalidate();
    }

    public void setNowStation(int nowStation) {
        this.nowStation = nowStation;
        invalidate();
    }

    public void setInStop(boolean inStop) {
        isInStop = inStop;
        isOutStop = !inStop;
        invalidate();
    }

    public void setOutStop(boolean outStop) {
        isOutStop = outStop;
        isInStop = !outStop;
        invalidate();
    }
}
