package com.example.sensorall.ui.dashboard;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensorall.R;
import com.example.sensorall.databinding.FragmentNotificationsBinding;

public class DashboardFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView accelerometerValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        accelerometerValue = view.findViewById(R.id.accelerometer_value);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String accelerometerData = "Value of Gravity \nX: " + x + "\nY: " + y + "\nZ: " + z;
        accelerometerValue.setText(accelerometerData);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // You can handle changes in sensor accuracy here if needed
    }
}