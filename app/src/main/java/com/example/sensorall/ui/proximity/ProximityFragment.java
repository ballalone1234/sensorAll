package com.example.sensorall.ui.proximity;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sensorall.R;

public class ProximityFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView accelerometerValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_proximity, container, false);
        accelerometerValue = view.findViewById(R.id.accelerometer_value);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("B6301095");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float cm = event.values[0];
        String text = cm <= 5 ? "Detect" : "No Detect";
        String accelerometerData = "Value of Proximity \nSensor: " + cm + " cm \n" + text;
        accelerometerValue.setText(accelerometerData);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // You can handle changes in sensor accuracy here if needed
    }
}