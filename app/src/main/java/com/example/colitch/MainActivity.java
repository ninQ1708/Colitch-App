package com.example.colitch;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    RelativeLayout r;
    int num  = 0;
    TextView t;
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = 0;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            r = findViewById(R.id.rlVar1);
            t = findViewById(R.id.tv);
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {

                if(num==0) {
                    r.setBackgroundResource(R.color.pink);
                    t.setBackgroundResource(R.color.pink_2);
                    num=1;
                }
                else if(num==1){
                    r.setBackgroundResource(R.color.blue);
                    t.setBackgroundResource(R.color.blue_2);
                    num=2;
                }
                else if(num==2){
                    r.setBackgroundResource(R.color.green);
                    t.setBackgroundResource(R.color.green_2);
                    num=3;
                }
                else if(num==3){
                    r.setBackgroundResource(R.color.red);
                    t.setBackgroundResource(R.color.red_2);
                    num=4;
                }
                else if(num==4){
                    r.setBackgroundResource(R.color.purple);
                    t.setBackgroundResource(R.color.purple_2);
                    num=5;
                }
                else if(num==5){
                    r.setBackgroundResource(R.color.yellow);
                    t.setBackgroundResource(R.color.yellow_2);
                    num=0;
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}