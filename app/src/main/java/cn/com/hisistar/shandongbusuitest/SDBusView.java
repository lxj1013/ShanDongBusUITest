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
    private static final int STATION_LIMIT = 32;

    public static final int IN_STOP = 1;
    public static final int OUT_STOP = 2;

    private Context mContext;

    private Paint mPaint;
    private Paint mLinePaint;
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mMiddleTextPaint;
    private Paint mInOutTextPaint;
    private Paint mEraserPaint;

    private RectF mLeftRectF;
    private RectF mRightRectF;
    private float mRectFWidth;

    private Path mLinePath;
    private Path mCirclePath;
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

    private int colorFlag = 0;
    private int mTotalStation = 0;
    private int mNowStation = 1;
    private int stopState = 0;
    private int inStopFlag = 0;
    private int outStopFlag = 0;

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
//        mBusStationList.add(new BusStation("公交交交南南站", false));
//        mBusStationList.add(new BusStation("潢潼", false));
//        mBusStationList.add(new BusStation("东河路兴业路", false));
//        mBusStationList.add(new BusStation("俊知工业园", false));
//        mBusStationList.add(new BusStation("恒峰电缆", false));
//        mBusStationList.add(new BusStation("国际环保展示中", false));
//        mBusStationList.add(new BusStation("一环集团", false));
//        mBusStationList.add(new BusStation("江南和院", false));
//        mBusStationList.add(new BusStation("西花园三村", false));
//        mBusStationList.add(new BusStation("西花园", false));
//        mBusStationList.add(new BusStation("丰泽苑西区", false));
//        mBusStationList.add(new BusStation("武警医院", false));
//        mBusStationList.add(new BusStation("环科园成人学校", false));
//        mBusStationList.add(new BusStation("绿园新村", false));
//        mBusStationList.add(new BusStation("宜兴日报社", false));
//        mBusStationList.add(new BusStation("迎宾路", false));
//        mBusStationList.add(new BusStation("老干部大学", false));
//        mBusStationList.add(new BusStation("王府", false));
//        mBusStationList.add(new BusStation("人民医院34", false));
//        mBusStationList.add(new BusStation("沁园", false));
//        mBusStationList.add(new BusStation("水浮桥", false));
//        mBusStationList.add(new BusStation("解放广场", false));
//        mBusStationList.add(new BusStation("东虹桥", false));
//        mBusStationList.add(new BusStation("东虹新村", false));
//        mBusStationList.add(new BusStation("东方明珠", false));
//        mBusStationList.add(new BusStation("临溪花苑", false));
//        mBusStationList.add(new BusStation("妇幼保健所", false));
//        mBusStationList.add(new BusStation("东氿一号", false));
//        mBusStationList.add(new BusStation("东氿广场", false));
//        mBusStationList.add(new BusStation("苏宁天天氿御城", false));
//        mBusStationList.add(new BusStation("城东公交公交总总站", false));
//        mBusStationList.add(new BusStation("城东公交公交总总站", false));
        //32
        mBusStationList.add(new BusStation("壹壹壹壹壹壹壹壹壹壹壹壹壹", false));
        mBusStationList.add(new BusStation("贰贰贰贰贰贰贰贰贰贰贰贰贰", false));
        mBusStationList.add(new BusStation("弎弎弎弎弎弎弎弎弎弎弎弎", false));
        mBusStationList.add(new BusStation("肆肆肆肆肆肆肆肆肆肆肆肆", false));
        mBusStationList.add(new BusStation("伍伍伍伍伍伍伍伍伍伍伍伍", false));
        mBusStationList.add(new BusStation("陆陆陆陆陆陆陆陆陆陆陆陆", false));
        mBusStationList.add(new BusStation("柒", false));
        mBusStationList.add(new BusStation("捌", false));
        mBusStationList.add(new BusStation("玖", false));
        mBusStationList.add(new BusStation("拾", false));
        mBusStationList.add(new BusStation("十一", false));
        mBusStationList.add(new BusStation("十二", false));
        mBusStationList.add(new BusStation("十三", false));
        mBusStationList.add(new BusStation("十四", false));
        mBusStationList.add(new BusStation("十五", false));
        mBusStationList.add(new BusStation("十六", false));
        mBusStationList.add(new BusStation("十七", false));
        mBusStationList.add(new BusStation("十八", false));
        mBusStationList.add(new BusStation("十九", false));
        mBusStationList.add(new BusStation("廿", false));
        mBusStationList.add(new BusStation("二十一", false));
        mBusStationList.add(new BusStation("二十二", false));
        mBusStationList.add(new BusStation("二十三", false));
        mBusStationList.add(new BusStation("二十四", false));
        mBusStationList.add(new BusStation("二十五", false));
        mBusStationList.add(new BusStation("二十六", false));
        mBusStationList.add(new BusStation("二十七", false));
        mBusStationList.add(new BusStation("二十八", false));
        mBusStationList.add(new BusStation("二十九", false));
        mBusStationList.add(new BusStation("卅", false));
        mBusStationList.add(new BusStation("三十一三十一三十一三十一", false));
        mBusStationList.add(new BusStation("三十二", false));
        mBusStationList.add(new BusStation("三十三", false));
        mBusStationList.add(new BusStation("三十四", false));
        mBusStationList.add(new BusStation("三十五三十五三十五三十五", false));

    }

    public SDBusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        cleanAll();

//        drawCoordinate(canvas);


        /*if ((stopState == IN_STOP) && (inStopFlag == 0)) {
            drawInOutStation(canvas, mNowStation, true);
            inStopFlag = 1;
            outStopFlag = 0;
            return;
        }

        if ((stopState == OUT_STOP) && (outStopFlag == 0)) {

            drawInOutStation(canvas, mNowStation, false);
            outStopFlag = 1;
            inStopFlag = 0;
            return;
        }*/
        mTotalStation = mBusStationList.size();
        if (mTotalStation<3)
            return;
        drawLine(canvas);

        drawCircleWithStroke(canvas, mTotalStation);

        drawText(canvas, mTotalStation, mNowStation);

        drawMiddleInfo(canvas, mNowStation);
//        canvas.drawRect(mLeftRectF,mPaint);


    }

    private void drawMiddleInfo(Canvas canvas, int nowStation) {
        String now;
        String next;
        String leftInfo;
        String rightInfo;
        float textHeight;
        float leftWidth;
        float rightWidth;
        float infoWidth;

        int textSize = 80;
        float cx = mViewWidth / 2.0f;
        float cy = mViewHeight / 2.0f;
        float lx = mPaddingLeft * 2.5f;
        float ly = mViewHeight / 2.0f;
        float rx = mViewWidth / 2.0f + mPaddingLeft * 1.5f;
        float ry = mViewHeight / 2.0f;

        mMiddleTextPaint.setAntiAlias(true);
        mMiddleTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mMiddleTextPaint.setTextSize(textSize);
        mMiddleTextPaint.setColor(Color.BLACK);

        now = mBusStationList.get(nowStation - 1).getStationName();
        leftInfo = "当前站: ";
        infoWidth = mViewWidth / 2.0f - mPaddingLeft * 2.5f;
        if (nowStation < mBusStationList.size()) {

            next = mBusStationList.get(nowStation).getStationName();
//            leftInfo = "当前站:" + "公交南南南";
//            rightInfo = "下一站:" + "潢潼潢潼潢潼";

            if (nowStation != mBusStationList.size() - 1) {
                rightInfo = "下一站: ";
            } else {
                rightInfo = "下一站为终点站: ";
            }

            leftWidth = mMiddleTextPaint.measureText(leftInfo + now);
            rightWidth = mMiddleTextPaint.measureText(rightInfo + next);


            if (stopState == IN_STOP) {
                while ((leftWidth > infoWidth) || (rightWidth > infoWidth)) {
                    textSize--;
                    mMiddleTextPaint.setTextSize(textSize);
                    leftWidth = mMiddleTextPaint.measureText(leftInfo + now);
                    rightWidth = mMiddleTextPaint.measureText(rightInfo + next);
                }
                textHeight = getFontHeight(mMiddleTextPaint);
                canvas.drawText(leftInfo, lx, ly + textHeight / 4.0f, mMiddleTextPaint);
                canvas.drawText(rightInfo, rx, ry + textHeight / 4.0f, mMiddleTextPaint);
                mMiddleTextPaint.setColor(Color.RED);
                canvas.drawText(now, lx + mMiddleTextPaint.measureText(leftInfo), ly + textHeight / 4.0f, mMiddleTextPaint);
                canvas.drawText(next, rx + mMiddleTextPaint.measureText(rightInfo), ry + textHeight / 4.0f, mMiddleTextPaint);
            } else {
                infoWidth = mViewWidth - mPaddingLeft * 5.0f;
                if (nowStation != mBusStationList.size() - 1) {
                    rightInfo = "下一站: ";
                } else {
                    rightInfo = "下一站为终点站: ";
                }
                rightWidth = mMiddleTextPaint.measureText(rightInfo + next);

                while (rightWidth > infoWidth) {
                    textSize--;
                    mMiddleTextPaint.setTextSize(textSize);
                    rightWidth = mMiddleTextPaint.measureText(rightInfo + next);
                }
                textHeight = getFontHeight(mMiddleTextPaint);
                canvas.drawText(rightInfo, cx - rightWidth / 2.0f, cy + textHeight / 4.0f, mMiddleTextPaint);
                mMiddleTextPaint.setColor(Color.RED);
                canvas.drawText(next, cx - rightWidth / 2.0f + mMiddleTextPaint.measureText(rightInfo), cy + textHeight / 4.0f, mMiddleTextPaint);
            }
        } else {
            infoWidth = mViewWidth - mPaddingLeft * 7.0f;
            leftInfo = "当前站为终点站: ";
            leftWidth = mMiddleTextPaint.measureText(leftInfo + now);

            while (leftWidth > infoWidth) {
                textSize--;
                mMiddleTextPaint.setTextSize(textSize);
                leftWidth = mMiddleTextPaint.measureText(leftInfo + now);
            }
            textHeight = getFontHeight(mMiddleTextPaint);
            canvas.drawText(leftInfo, cx - leftWidth / 2.0f, cy + textHeight / 4.0f, mMiddleTextPaint);
            mMiddleTextPaint.setColor(Color.RED);
            canvas.drawText(now, cx - leftWidth / 2.0f + mMiddleTextPaint.measureText(leftInfo), cy + textHeight / 4.0f, mMiddleTextPaint);
        }

    }

    private void drawInOutStation(Canvas canvas, int nowStation, boolean stopFlag) {

        float textSize = 60;
        float tx = mPaddingLeft;
        float ty = mViewHeight / 5.0f;
        float cx = mViewWidth / 2.0f;
        float cy = mViewHeight / 2.0f;
        float bx = mViewWidth - mPaddingLeft;
        float by = mViewHeight - 40.0f;
        float middleWidth;
        float textHeight;
        float infoWidth = mViewWidth - mPaddingLeft * 2;
        String station;
        mInOutTextPaint.setAntiAlias(true);
        mInOutTextPaint.setTextSize(textSize);

        String topInfo = "欢迎乘坐本班公交车";
        String middleInfo;
        String bottomInfo;
        String now = mBusStationList.get(nowStation - 1).getStationName();
        String next;

        canvas.drawText(topInfo, tx, ty, mInOutTextPaint);

        if (nowStation < mBusStationList.size()) {
            if (stopFlag) {
                middleInfo = "当前站: ";
                bottomInfo = "有下车的乘客请携带好随身物品准备下车";
                mInOutTextPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(bottomInfo, bx, by, mInOutTextPaint);
                mInOutTextPaint.setTextAlign(Paint.Align.LEFT);
                textSize = 80;
                mInOutTextPaint.setTextSize(textSize);
                middleWidth = mInOutTextPaint.measureText(middleInfo + now);
                while (middleWidth > infoWidth) {
                    textSize--;
                    mInOutTextPaint.setTextSize(textSize);
                    middleWidth = mInOutTextPaint.measureText(middleInfo + now);
                }
                textHeight = getFontHeight(mInOutTextPaint);
                canvas.drawText(middleInfo, cx - middleWidth / 2.0f, cy + textHeight / 4.0f, mInOutTextPaint);
                mInOutTextPaint.setColor(Color.RED);
                canvas.drawText(now, cx - middleWidth / 2.0f + mInOutTextPaint.measureText(middleInfo), cy + textHeight / 4.0f, mInOutTextPaint);
            } else {
                middleInfo = "下一站: ";
                if (nowStation == mBusStationList.size() - 1) {
                    middleInfo = "下一站为终点站: ";
                }
                next = mBusStationList.get(nowStation).getStationName();
                bottomInfo = "有下车的乘客请提前做好下车准备";
                mInOutTextPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(bottomInfo, bx, by, mInOutTextPaint);
                mInOutTextPaint.setTextAlign(Paint.Align.LEFT);
                textSize = 80;
                mInOutTextPaint.setTextSize(textSize);
                middleWidth = mInOutTextPaint.measureText(middleInfo + next);
                while (middleWidth > infoWidth) {
                    textSize--;
                    mInOutTextPaint.setTextSize(textSize);
                    middleWidth = mInOutTextPaint.measureText(middleInfo + next);
                }
                textHeight = getFontHeight(mInOutTextPaint);
                canvas.drawText(middleInfo, cx - middleWidth / 2.0f, cy + textHeight / 4.0f, mInOutTextPaint);
                mInOutTextPaint.setColor(Color.RED);
                canvas.drawText(next, cx - middleWidth / 2.0f + mInOutTextPaint.measureText(middleInfo), cy + textHeight / 4.0f, mInOutTextPaint);
            }
        } else {
            middleInfo = "当前站为终点站: ";
            bottomInfo = "有下车的乘客请携带好随身物品准备下车";
            mInOutTextPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(bottomInfo, bx, by, mInOutTextPaint);
            mInOutTextPaint.setTextAlign(Paint.Align.LEFT);
            textSize = 80;
            mInOutTextPaint.setTextSize(textSize);
            middleWidth = mInOutTextPaint.measureText(middleInfo + now);
            while (middleWidth > infoWidth) {
                textSize--;
                mInOutTextPaint.setTextSize(textSize);
                middleWidth = mInOutTextPaint.measureText(middleInfo + now);
            }
            textHeight = getFontHeight(mInOutTextPaint);
            canvas.drawText(middleInfo, cx - middleWidth / 2.0f, cy + textHeight / 4.0f, mInOutTextPaint);
            mInOutTextPaint.setColor(Color.RED);
            canvas.drawText(now, cx - middleWidth / 2.0f + mInOutTextPaint.measureText(middleInfo), cy + textHeight / 4.0f, mInOutTextPaint);

        }

        postInvalidateDelayed(10 * 1000);

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

    private void drawCircleWithStroke(Canvas canvas, int totalStation) {
        if (totalStation > STATION_LIMIT) {
            totalStation = STATION_LIMIT;
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

        for (int i = 0; i < totalStation; i++) {
            mPoint = mCirclePointList.get(i);
            mCirclePath.addCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, Path.Direction.CW);
        }

        canvas.drawPath(mCirclePath, mCirclePaint);

//        setFlashingCircle(canvas);
    }

    private void drawCircleWithFill(Canvas canvas, int totalStation, int nowStation) {

        if (totalStation > STATION_LIMIT) {
            totalStation = STATION_LIMIT;
        }
        if (nowStation > totalStation) {
            nowStation = totalStation;
        }
        if (nowStation < 1) {
            nowStation = 1;
        }

        for (int i = 0; i < nowStation - 1; i++) {
            mPoint = mCirclePointList.get(i);
            mCirclePath1.addCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, Path.Direction.CW);
        }
        for (int i = nowStation; i < totalStation; i++) {
            mPoint = mCirclePointList.get(i);
            mCirclePath2.addCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, Path.Direction.CW);
        }

        mPoint = mCirclePointList.get(nowStation - 1);


        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(COLOR_RED);
        canvas.drawPath(mCirclePath1, mCirclePaint);

        if (stopState != IN_STOP) {
            canvas.drawCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, mCirclePaint);
        }

        mCirclePaint.setColor(COLOR_GREEN);
        canvas.drawPath(mCirclePath2, mCirclePaint);
    }


    private void setFlashingCircle(Canvas canvas, int position) {
        if (stopState == IN_STOP) {
            if (colorFlag == 0) {
                mCirclePaint.setColor(COLOR_YELLOW);
                colorFlag = 1;
            } else {
                mCirclePaint.setColor(COLOR_GREEN);
                colorFlag = 0;
            }
            mPoint = mCirclePointList.get(position);
            canvas.drawCircle(mPoint.getX(), mPoint.getY(), mCircleRadius, mCirclePaint);
            postInvalidateDelayed(1000);
        }
    }


    private void drawText(Canvas canvas, int totalStation, int nowStation) {
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);

        float textHeight = getFontHeight(mTextPaint);
        float textWidth;
        float textWidth1;
        String text;

        mEraserPaint.setAntiAlias(true);
        mEraserPaint.setStyle(Paint.Style.FILL);
        mEraserPaint.setStrokeWidth(mCircleRadius * 2 + 10);
        mEraserPaint.setColor(Color.WHITE);


        String threePoint = "●●●";
        textWidth = mTextPaint.measureText(threePoint) / 2.0f;
        textWidth1 = textWidth > mCircleRadius ? textWidth : mCircleRadius;
        textWidth1 += 3;

        if (totalStation > STATION_LIMIT) {

            if ((totalStation - nowStation + 4 > STATION_LIMIT) && (nowStation > 3)) {
                drawCircleWithFill(canvas, totalStation, 3);
                setFlashingCircle(canvas, 3 - 1);
                drawNumber(canvas, nowStation - 1);
                drawStation(canvas, nowStation - 1);
            } else if (nowStation < 4) {
                drawCircleWithFill(canvas, totalStation, nowStation);
                setFlashingCircle(canvas, nowStation - 1);
            } else {
                drawCircleWithFill(canvas, totalStation, nowStation - (totalStation - STATION_LIMIT));
                setFlashingCircle(canvas, nowStation - (totalStation - STATION_LIMIT) - 1);
                drawNumber(canvas, (totalStation - STATION_LIMIT + 3) - 1);
                drawStation(canvas, (totalStation - STATION_LIMIT + 3) - 1);
            }

            float offsetWidth = mTextPaint.measureText("建") * 3;

            if (nowStation > 3) {
                mPoint = mCirclePointList.get(1);
//                mEraserPaint.setColor(Color.WHITE);
                canvas.drawLine(mPoint.getX() - textWidth1, mPoint.getY(), mPoint.getX() + textWidth1, mPoint.getY(), mEraserPaint);
                canvas.drawText(threePoint, mPoint.getX() - textWidth, mPoint.getY() + textHeight / 4.0f, mTextPaint);

//                mEraserPaint.setColor(0xddffff00);
                canvas.drawRect(mPoint.getX() - offsetWidth, mPoint.getY() - mCircleRadius - textHeight * 2, mPoint.getX() + offsetWidth, mPoint.getY() - mCircleRadius, mEraserPaint);

            } else {
                drawNumber(canvas, 2);
                drawStation(canvas, 2);
            }


            if (totalStation - nowStation + 3 > STATION_LIMIT) {
                mPoint = mCirclePointList.get(mCirclePointList.size() - 2);
//                mEraserPaint.setColor(Color.WHITE);
                canvas.drawLine(mPoint.getX() - textWidth1, mPoint.getY(), mPoint.getX() + textWidth1, mPoint.getY(), mEraserPaint);
                canvas.drawText(threePoint, mPoint.getX() - textWidth, mPoint.getY() + textHeight / 4.0f, mTextPaint);

//                mEraserPaint.setColor(0xddffff00);
                canvas.drawRect(mPoint.getX() - offsetWidth, mPoint.getY() + mCircleRadius, mPoint.getX() + offsetWidth, mPoint.getY() + mCircleRadius + textHeight * 2 + mTextPadding, mEraserPaint);

            }


        } else {

            drawCircleWithFill(canvas, totalStation, nowStation);
            int position = nowStation - 1;
            setFlashingCircle(canvas, position);
            drawNumber(canvas, 2);
            drawStation(canvas, 2);
        }


    }

    private void drawStation(Canvas canvas, int begin) {
        mTextPointList.addAll(mCirclePointList);


        float textHeight = getFontHeight(mTextPaint);
        float textWidth = 0f;
        float textWidth1 = 0f;
        float textWidth2 = 0f;
        String text = "";
        String text1 = "";
        String text2 = "";
//        canvas.drawText("一公交南站", 500, 300, mTextPaint);

        for (int i = 0; i < mCirclePointList.size(); i++) {
            mPoint = mCirclePointList.get(i);
            if ((i % 2 != 0) || (i == mCirclePointList.size() - 1)) {
                mTextPointList.set(i, new Point(mPoint.getX(), mPoint.getY() - (mCircleRadius + mTextPadding)));
            } else {
                mTextPointList.set(i, new Point(mPoint.getX(), mPoint.getY() + mCircleRadius + textHeight));
            }
        }


        for (int i = 1; i < mTextPointList.size() - 1; i++) {

            mPoint = mTextPointList.get(i);
            text = mBusStationList.get(i + begin - 2).getStationName();

            if (text.length() > 6) {
                if (text.length() > 12) {
                    text = text.substring(0, 12);
                }
                text1 = text.substring(0, text.length() / 2);
                text2 = text.substring(text.length() / 2);
                textWidth1 = mTextPaint.measureText(text1);
                textWidth2 = mTextPaint.measureText(text2);

                if (i % 2 != 0) {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY() - textHeight, mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY(), mTextPaint);
                } else {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY(), mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY() + textHeight, mTextPaint);
                }
            } else {
                textWidth = mTextPaint.measureText(text);
                canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY(), mTextPaint);
            }
        }

        for (int i = 0; i < mTextPointList.size(); i += mTextPointList.size() - 1) {
            mPoint = mTextPointList.get(i);
            if (i == 0) {
                text = mBusStationList.get(i).getStationName();
            } else {
                text = mBusStationList.get(mTotalStation - 1).getStationName();
            }
            if (text.length() > 6) {
                if (text.length() > 12) {
                    text = text.substring(0, 12);
                }
                text1 = text.substring(0, text.length() / 2);
                text2 = text.substring(text.length() / 2);
                textWidth1 = mTextPaint.measureText(text1);
                textWidth2 = mTextPaint.measureText(text2);

                if ((i % 2 != 0) || (i == mTextPointList.size() - 1)) {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY() - textHeight, mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY(), mTextPaint);
                } else {
                    canvas.drawText(text1, mPoint.getX() - textWidth1 / 2.0f, mPoint.getY(), mTextPaint);
                    canvas.drawText(text2, mPoint.getX() - textWidth2 / 2.0f, mPoint.getY() + textHeight, mTextPaint);
                }
            } else {
                textWidth = mTextPaint.measureText(text);
                canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY(), mTextPaint);
            }
        }


    }

    private void drawNumber(Canvas canvas, int begin) {

        float textWidth;
        float textHeight = getFontHeight(mTextPaint);
        for (int i = 1; i < mCirclePointList.size() - 1; i++) {
            mPoint = mCirclePointList.get(i);

            String text = i + begin - 1 + "";
            textWidth = mTextPaint.measureText(text);
            canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY() + textHeight / 4.0f + 1, mTextPaint);
        }

        mPoint = mCirclePointList.get(0);
        String text = 1 + "";
        textWidth = mTextPaint.measureText(text);
        canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY() + textHeight / 4.0f + 1, mTextPaint);

        mPoint = mCirclePointList.get(mCirclePointList.size() - 1);
        text = mTotalStation + "";
        textWidth = mTextPaint.measureText(text);
        canvas.drawText(text, mPoint.getX() - textWidth / 2.0f, mPoint.getY() + textHeight / 4.0f + 1, mTextPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mMiddleHeight = mViewHeight / 5.0f;
        mPaddingTop = mMiddleHeight;
        mPaddingLeft = 1920 / 64 * 2.5f;
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
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, mViewHeight / 5 * 2, mViewWidth, mViewHeight / 5 * 2, mPaint);
        canvas.drawLine(0, mViewHeight / 5 * 3, mViewWidth, mViewHeight / 5 * 3, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mCirclePaint = new Paint();
        mTextPaint = new Paint();
        mMiddleTextPaint = new Paint();
        mInOutTextPaint = new Paint();
        mEraserPaint = new Paint();

        mLeftRectF = new RectF();
        mRightRectF = new RectF();
        mRectFWidth = 100.0f;

        mLinePath = new Path();
        mCirclePath = new Path();
        mCirclePath1 = new Path();
        mCirclePath2 = new Path();

//        mPaddingTop = 5.0f;
//        mPaddingLeft = 10.0f;
//        mPaddingRight = 5.0f;
        mTextSize = 20.0f;
        mTextPadding = 6.0f;
        mCircleRadius = 15.0f;
        mLineLength = 0;
        mLineWidth = mCircleRadius / 5.0f;

        mBusStationList = new ArrayList<>();
        mCirclePointList = new ArrayList<>();
        mTextPointList = new ArrayList<>();
        initStations();
    }

    private void cleanAll() {
        mPaint.reset();
        mLinePaint.reset();
        mCirclePaint.reset();
        mTextPaint.reset();
        mMiddleTextPaint.reset();
        mInOutTextPaint.reset();
        mEraserPaint.reset();

        mLinePath.reset();
        mCirclePath.reset();
        mCirclePath1.reset();
        mCirclePath2.reset();

//        mBusStationList.clear();
        mCirclePointList.clear();
        mTextPointList.clear();

    }


    public void setBusStationList(List<BusStation> busStationList) {
        this.mBusStationList = busStationList;
        invalidate();
    }

    public void setNowStation(int nowStation) {
        if (nowStation < 1) {
            nowStation = 1;
        }
        this.mNowStation = nowStation < mBusStationList.size() ? nowStation : mBusStationList.size();

        invalidate();
    }

    public void setStopState(int stopState) {
        this.stopState = stopState;
//        invalidate();
    }
}
