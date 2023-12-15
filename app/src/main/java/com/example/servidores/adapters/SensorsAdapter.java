package com.example.servidores.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servidores.R;
import com.example.servidores.models.Sensores;
import com.example.servidores.request.RequestSensors;
import com.example.servidores.request.SwitchChangeListener;
import com.example.servidores.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorsAdapter  extends RecyclerView.Adapter<SensorsAdapter.SensorsHolder> {
    private List<Sensores> listsen;
    private SwitchChangeListener switchChangeListener;
    public SensorsAdapter(List<Sensores> listsen, SwitchChangeListener switchChangeListener) {
        this.listsen = listsen;
        this.switchChangeListener = switchChangeListener;
    }
    public void setSensorDataList(List<Sensores> sensList) {
        this.listsen = sensList;
    }

    @NonNull
    @Override
    public SensorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(parent.getContext());
        View v = lyf.inflate(R.layout.activity_sensor_items,parent,false);
        return new SensorsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorsHolder holder, int position) {
        Sensores sen = listsen.get(position);
        holder.setData(sen);
    }

    @Override
    public int getItemCount() {
        return listsen.size();
    }


    public class SensorsHolder extends RecyclerView.ViewHolder {
        TextView nam;
        TextView dat;
        Switch sw;
        SwitchChangeListener switchChangeListener;
        public SensorsHolder(@NonNull View itemView) {
            super(itemView);
            nam = itemView.findViewById(R.id.sensor);
            dat = itemView.findViewById(R.id.data);
            sw = itemView.findViewById(R.id.actor);
            switchChangeListener = new SwitchChangeListener() {
                @Override
                public void onSwitchChanged(String feed_key, boolean isChecked) {

                }
            };
            sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Sensores sen = listsen.get(position);
                    switchChangeListener.onSwitchChanged(sen.getFeed_key(), isChecked);

                    if(isChecked){
                        sw.setEnabled(false);
                        performGetRequest(sen.getFeed_key());
                    }
                }
            });
        }

        private void performGetRequest(String feedKey) {
            RequestSensors requestSensors = RetrofitClient.getInstance().create(RequestSensors.class);
            Call<List<Sensores>> call = null;

            if ("acceso".equals(feedKey)) {
                call = requestSensors.abrirPuerta();
            } else if ("alarma".equals(feedKey)) {
                call = requestSensors.apagarAlarma();
            } else if ("leds".equals(feedKey)) {
                call = requestSensors.modificarluces();
            }
            if (call != null) {
                call.enqueue(new Callback<List<Sensores>>() {
                    @Override
                    public void onResponse(Call<List<Sensores>> call, Response<List<Sensores>> response) {
                        List<Sensores> sensoresList = response.body();
                        sw.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Call<List<Sensores>> call, Throwable t) {
                        sw.setEnabled(true);
                    }
                });
            }
        }
        public void setData(Sensores sen) {
            if("normal".equals(sen.getTipo())){
                nam.setText(sen.getFeed_key());
                sw.setVisibility(View.GONE);
                if("temperatura".equals(sen.getFeed_key())){
                    sw.setVisibility(View.VISIBLE);
                    sw.setText("Ventilacion");
                    sw.setClickable(false);
                    sw.setFocusable(false);
                    sw.setFocusableInTouchMode(false);
                    if(Float.parseFloat(sen.getValue())>23){
                        sw.setChecked(true);
                    }
                    else {
                        sw.setChecked(false);
                    }
                }
                else if("humo".equals(sen.getFeed_key())){
                    sw.setVisibility(View.VISIBLE);
                    sw.setText("Alarma");
                    if("Alarma".equals(sen.getFeed_key()) && "1".equals(sen.getValue())){
                        sw.setClickable(true);
                        sw.setFocusable(true);
                        sw.setFocusableInTouchMode(true);
                        sw.setChecked(true);
                    }
                    else{
                        sw.setClickable(false);
                        sw.setFocusable(false);
                        sw.setFocusableInTouchMode(false);
                        sw.setChecked(false);
                    }
                }
            }
            else if("actuador".equals(sen.getTipo())){
                if("acceso".equals(sen.getFeed_key())){
                    nam.setText(sen.getFeed_key());
                    sw.setText("Puerta");
                    sw.setVisibility(View.VISIBLE);
                    if("1".equals(sen.getValue())){
                        sw.setClickable(false);
                        sw.setFocusable(false);
                        sw.setFocusableInTouchMode(false);
                        sw.setChecked(true);
                    }
                    else {
                        sw.setClickable(true);
                        sw.setFocusable(true);
                        sw.setFocusableInTouchMode(true);
                        sw.setChecked(false);
                    }
                }
                else if("leds".equals(sen.getFeed_key())){
                    sw.setText("Luz");
                    nam.setVisibility(View.GONE);
                    dat.setVisibility(View.GONE);
                }
            }
            else {
                sw.setVisibility(View.GONE);
                nam.setVisibility(View.GONE);
                dat.setVisibility(View.GONE);
            }
            dat.setText(String.format("%.2f",Float.parseFloat(sen.getValue())));

        }
    }
}
