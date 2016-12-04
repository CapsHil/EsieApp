package com.capshil.esieapp.controller.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capshil.esieapp.R;

public class TemperatureFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView temp;

    public TemperatureFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) this.getActivity().getSystemService(Activity.SENSOR_SERVICE);
        Sensor temp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_temperature, container, false);
        temp = (TextView) getActivity().findViewById(R.id.temp);
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float tmp = event.values[0];
        temp.setText(tmp+" C");
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @Override
    public void onStop() {
        super.onStop();
        this.unregisterSensorListener();
    }

    private void registerSensorListener() {
        temp.setText("20 C");
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).get(0), SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}
