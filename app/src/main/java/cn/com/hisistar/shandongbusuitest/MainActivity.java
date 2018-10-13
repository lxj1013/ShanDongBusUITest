package cn.com.hisistar.shandongbusuitest;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lineNumTv;
    private TextView firstStationTv;
    private TextView endStationTv;
    private SDBusView mSDBusView;
    private Button inBtn;
    private Button outBtn;
    private Button addBtn;
    private int nowStation;

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


    }
}
