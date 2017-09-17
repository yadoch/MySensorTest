package tw.com.abc.mysensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager smgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        smgr =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors=smgr.getSensorList(Sensor.TYPE_ALL);
        for( Sensor sensor : sensors){
            String name = sensor.getName();
            String v = sensor.getVendor();
            Log.i("brad",name+":"+v);
        }
    }
}
