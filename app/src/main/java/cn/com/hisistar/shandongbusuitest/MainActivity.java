package cn.com.hisistar.shandongbusuitest;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lineNumTv;
    private TextView firstStationTv;
    private TextView endStationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstStationTv = findViewById(R.id.first_station_tv);
        endStationTv = findViewById(R.id.end_station_tv);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/STXINGKA.TTF");

        firstStationTv.setTypeface(tf);
        endStationTv.setTypeface(tf);


    }
}
