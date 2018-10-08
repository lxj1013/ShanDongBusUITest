package cn.com.hisistar.shandongbusuitest;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*AssetManager mgr = getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/STXINGKA.TTF");
        TextView textView = findViewById(R.id.helloworld);
        textView.setTypeface(tf);
        textView.setTextSize(30);
        textView.setText("公交南站城东公交总站");*/

    }
}
