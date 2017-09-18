package tw.com.abc.mysensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager smgr;
    private MySensorListener mySensorListener;
    private Sensor sensor;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
      //  tv=(TextView)findViewById(R.id.tv);

    }
    private void init(){
        tv=(TextView)findViewById(R.id.tv);

        //1.列出所有的Sensor
        smgr =(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors=smgr.getSensorList(Sensor.TYPE_ALL);
        for( Sensor sensor : sensors){
            String name = sensor.getName();
            String v = sensor.getVendor();
            Log.i("brad",name+":"+v);
        }
        //2.加速器
       // sensor = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mySensorListener=new MySensorListener();

        //3.環境溫度感應器-判斷有無範例
        sensor = smgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(sensors ==null){
            Log.i("brad","Not Support!!");
        }
        mySensorListener=new MySensorListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        smgr.registerListener(mySensorListener,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        smgr.unregisterListener(mySensorListener);
    }

    private class MySensorListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] vs =event.values;
            //Log.i("brad",vs[0] + "\n");

            tv.setText("X="+(int)vs[0]*10+"\n"+
                        "Y="+(int)vs[1]*10+"\n"+
                        "Z="+(int)vs[2]*10+"\n");

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
