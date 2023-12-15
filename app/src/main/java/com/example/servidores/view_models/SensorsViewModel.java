package com.example.servidores.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.servidores.models.SensorList;
import com.example.servidores.repository.SensorsRepository;

public class SensorsViewModel extends ViewModel {
    private MutableLiveData<SensorList> sensores;
    private SensorsRepository sensorsRepository;
    public LiveData<SensorList> getSensores (String id) {
        if (sensores == null) {
            sensores = new MutableLiveData<>();
            loadSensors(id);
        }
        return sensores;
    }

    private void loadSensors (String id) {
        if (sensorsRepository == null) {
            sensorsRepository = new SensorsRepository();
        }
        LiveData<SensorList> repositoryLiveData = sensorsRepository.getSensors(id);
        repositoryLiveData.observeForever(new Observer<SensorList>() {
            @Override
            public void onChanged(SensorList sensList) {
                sensores.setValue(sensList);
            }
        });
    }
}
