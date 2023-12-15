package com.example.servidores.models;

import java.util.List;

public class SensorList {
    private List<Sensores> sensorDataList;

    public SensorList(List<Sensores> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    public List<Sensores> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<Sensores> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }
}
