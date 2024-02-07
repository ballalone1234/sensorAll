package com.example.sensorall.ui.light;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sensorall.R;

public class LightFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView accelerometerValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proximity, container, false);
        accelerometerValue = view.findViewById(R.id.accelerometer_value);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux  = event.values[0];
        String text =
                lux > 20000 ? "This light will blind you":
                lux > 5000  ? "Incredibly bright":
                lux > 1000 ? "Normal":
                lux > 100 ? "Grey":
                lux > 1 ? "Dark":
                lux == 0 ? "Pitch black" : ""
                ;
        String light_data = "Value of Light sensor \nSensor: " + lux + " lux\n" + text;
        accelerometerValue.setText(light_data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // You can handle changes in sensor accuracy here if needed
    }
}