package com.example.sensorall.ui.temperature;

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

public class TemperatureFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView accelerometerValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proximity, container, false);
        accelerometerValue = view.findViewById(R.id.accelerometer_value);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float tem  = event.values[0];
        String text =
                tem == 100 ? "Boiling point of water":
                        tem > 37.5 ? "No detect":
                                  tem > 25 ? "Typical human body temperature":
                                         tem > 23 ? "Room temperature":
                                                tem == 0 ? "Melting point of ice" : "Snowing"
                ;
        String tem_data = "Value of Temperature sensor \nSensor: " + tem + " celsius\n" + text;
        accelerometerValue.setText(tem_data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // You can handle changes in sensor accuracy here if needed
    }
}