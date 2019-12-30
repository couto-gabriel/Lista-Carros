package br.com.listacarros.view.detail.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.listacarros.R;


public class VehicleEquipmentViewHolder extends RecyclerView.ViewHolder {

    public TextView mVehicleEquipamentItemTextView;

    public VehicleEquipmentViewHolder(@NonNull View itemView) {
        super(itemView);

        mVehicleEquipamentItemTextView = itemView.findViewById(R.id.vehicleDetailEquipamentItemTextView);
    }
}
