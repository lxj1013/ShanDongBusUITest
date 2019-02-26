package cn.com.hisistar.shandongbusuitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static cn.com.hisistar.shandongbusuitest.FileUtil.IMAGE;
import static cn.com.hisistar.shandongbusuitest.FileUtil.VIDEO;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String PROGRAM_PATH_CHANGED = "cn.com.hisistar.broadcast.PROGRAM_PATH_CHANGED";

    private TextView lineNumTv;
    private TextView firstStationTv;
    private TextView endStationTv;
    private SDBusView mSDBusView;
    private Button inBtn;
    private Button outBtn;
    private Button addBtn;
    private int nowStation;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private UsbReceiver mUsbReceiver;
    private CountDownTimer countDownTimer;


    private RelativeLayout programLayout;
    private MyVideoView myVideoView;
    private ImageView imageView;
    private String programFilePath;
    private FileUtil fileUtil;
    private String[] programList;
    private String[] imageProgramList;
    private int i = 0;
    private int j = 0;
    private int imageProgramSize = 0;
    private List mBusStationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstStationTv = findViewById(R.id.first_station_tv);
        endStationTv = findViewById(R.id.end_station_tv);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/STXINGKA.TTF");

        firstStationTv.setTypeface(tf);
        endStationTv.setTypeface(tf);

        mSDBusView = findViewById(R.id.sd_bus_view);
        inBtn = findViewById(R.id.in_stop_btn);
        outBtn = findViewById(R.id.out_stop_btn);
        addBtn = findViewById(R.id.add_stop_btn);
        nowStation = 1;


        programFilePath = StorageUtil.getDefaultStoragePath() + "/video_image_dir";
        programLayout = findViewById(R.id.program_layout);
        myVideoView = findViewById(R.id.video_view);
        imageView = findViewById(R.id.image_view);


        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(PROGRAM_PATH_CHANGED);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        registerReceiver();
        initStations();
        mSDBusView.setBusStationList(mBusStationList);


        inBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSDBusView.setStopState(SDBusView.IN_STOP);
                mSDBusView.setNowStation(nowStation);
            }
        });

        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSDBusView.setStopState(SDBusView.OUT_STOP);
                mSDBusView.setNowStation(nowStation);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowStation++;
                if (nowStation>35){
                    nowStation=1;
                }
            }
        });

        initPrograms();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
        unregisterReceiver(mUsbReceiver);

        if (myVideoView != null) {
            myVideoView.suspend();
        }
    }
    private void initStations() {
        mBusStationList = new ArrayList();
        mBusStationList.add(new BusStation("公交南站", false));
        mBusStationList.add(new BusStation("潢潼", false));
        mBusStationList.add(new BusStation("东河路兴业路", false));
        mBusStationList.add(new BusStation("东河路兴业路", false));
        mBusStationList.add(new BusStation("俊知工业园", false));
        mBusStationList.add(new BusStation("恒峰电缆", false));
        mBusStationList.add(new BusStation("国际环保展示中", false));
        mBusStationList.add(new BusStation("国际环保展示中", false));
        mBusStationList.add(new BusStation("一环集团", false));
        mBusStationList.add(new BusStation("江南和院", false));
        mBusStationList.add(new BusStation("西花园三村", false));
        mBusStationList.add(new BusStation("西花园", false));
        mBusStationList.add(new BusStation("西花园", false));
        mBusStationList.add(new BusStation("丰泽苑西区", false));
        mBusStationList.add(new BusStation("武警医院", false));
        mBusStationList.add(new BusStation("环科园成人学校", false));
        mBusStationList.add(new BusStation("绿园新村", false));
        mBusStationList.add(new BusStation("宜兴日报社", false));
        mBusStationList.add(new BusStation("迎宾路", false));
        mBusStationList.add(new BusStation("老干部大学", false));
        mBusStationList.add(new BusStation("老干部大学", false));
        mBusStationList.add(new BusStation("王府", false));
        mBusStationList.add(new BusStation("人民医院34", false));
        mBusStationList.add(new BusStation("沁园", false));
        mBusStationList.add(new BusStation("水浮桥", false));
        mBusStationList.add(new BusStation("解放广场", false));
        mBusStationList.add(new BusStation("解放广场", false));
        mBusStationList.add(new BusStation("东虹桥", false));
        mBusStationList.add(new BusStation("东虹新村", false));
        mBusStationList.add(new BusStation("东方明珠", false));
        mBusStationList.add(new BusStation("临溪花苑", false));
        mBusStationList.add(new BusStation("妇幼保健所", false));
        mBusStationList.add(new BusStation("东氿一号", false));
        mBusStationList.add(new BusStation("东氿广场", false));
        mBusStationList.add(new BusStation("苏宁天天氿御城", false));
        mBusStationList.add(new BusStation("城东公交公交总总站", false));
        mBusStationList.add(new BusStation("城东公交总站", false));

    }

    private void registerReceiver() {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        iFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        iFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        iFilter.addDataScheme("file");
        mUsbReceiver = new UsbReceiver();
        registerReceiver(mUsbReceiver, iFilter);
    }



    private void initPrograms() {
        i = 0;
        j = 0;
        imageProgramSize = 0;

        final File programPath = new File(programFilePath);

        if (!programPath.exists()) {
            programPath.mkdir();
        }
        fileUtil = new FileUtil();

        programList = fileUtil.getVideoAndImageProgramList(programPath);
        if (programList.length == 0) {
            programLayout.setVisibility(View.GONE);
            return;
        } else {
            programLayout.setVisibility(View.VISIBLE);
        }
        nextProgram();
    }

    public void nextProgram() {
        if (i == programList.length)
            i = 0;
        if (fileUtil.getFileType(programList[i]).equals(VIDEO)) {
            imageProgramSize = 0;
            myVideoView.setVisibility(View.VISIBLE);
            myVideoView.setVideoPath(programList[i]); // 指定视频文件的路径
            if (!myVideoView.isPlaying()) {
                myVideoView.start(); // 开始播放
            }
            i++;
        } else if (fileUtil.getFileType(programList[i]).equals(IMAGE)) {
            myVideoView.setVisibility(View.GONE);

            for (j = i; (imageProgramSize <= programList.length) && (fileUtil.getFileType(programList[i]).equals(IMAGE)); ) {
                i++;
                if (i == programList.length)
                    i = 0;
                imageProgramSize++;
            }
            imageProgramList = new String[imageProgramSize];
            for (int i = 0; i < imageProgramList.length; i++) {
                imageProgramList[i] = programList[j];
                Log.i(TAG, "imageProgramList[" + i + "]: " + imageProgramList[i]);
                j++;
                if (j == programList.length)
                    j = 0;
            }

            if (countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer = null;
            }

            countDownTimer = new CountDownTimer(imageProgramSize * 6200, 6000) {
                int k = 0;

                @Override
                public void onTick(long l) {
                    Log.i(TAG, "onTick: time = " + l);
                    Log.i(TAG, "onTick: imageProgramList[" + k + "] = " + imageProgramList[k]);
                    if (k < imageProgramSize)
                        Glide.with(MainActivity.this).load(imageProgramList[k]).dontAnimate().into(imageView);
                    k++;
                }

                @Override
                public void onFinish() {
                    nextProgram();
                }
            };
            countDownTimer.start();

        }
        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                nextProgram();
            }
        });
        myVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                myVideoView.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                return true;
            }
        });
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null)
                return;
            String action = intent.getAction();
            switch (action) {
                case PROGRAM_PATH_CHANGED:
//                    initViews();
                    initPrograms();
//                    initStations(TABLE_STATION_DOWN, mStationListDown1, mStationListDown2);
//                    initStations(TABLE_STATION_UP, mStationListUp1, mStationListUp2);
                    break;
                default:
                    break;
            }
        }
    }
}
