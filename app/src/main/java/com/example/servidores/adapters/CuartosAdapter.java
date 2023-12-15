package com.example.servidores.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servidores.R;
import com.example.servidores.models.Cuartos;
import com.example.servidores.views.SensoresYAdaptadores;

import java.util.List;

public class CuartosAdapter extends RecyclerView.Adapter<CuartosAdapter.ViewHolder> implements View.OnClickListener {
    private List<Cuartos> cuartoslist;
    private View.OnClickListener listener;

    public CuartosAdapter(List<Cuartos> cuartoslist) {
        this.cuartoslist = cuartoslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent,false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cuartos cua = cuartoslist.get(position);
        holder.setData(cua);
    }

    @Override
    public int getItemCount() {
        return cuartoslist.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvBody;
        TextView tvID;
        LinearLayout cuarto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvBody= itemView.findViewById(R.id.tvBody);
            tvID=itemView.findViewById(R.id.tvID);
            cuarto = itemView.findViewById(R.id.cuarto);
        }
        public void setData(Cuartos cua) {
            tvTitle.setText(cua.getNombre());
            tvBody.setText(cua.getPropietario());
            tvID.setText(cua.getId());
            cuarto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sensores = new Intent(itemView.getContext(), SensoresYAdaptadores.class);
                    sensores.putExtra("id","2");
                    itemView.getContext().startActivity(sensores);
                }
            });
        }
    }
}
