package br.com.listacarros.view.detail.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.listacarros.R;

public class VehiclePhotoViewHolder extends RecyclerView.ViewHolder {

    public ImageView mVehicleDetailPhotoItem;

    public VehiclePhotoViewHolder(@NonNull View itemView) {
        super(itemView);

        mVehicleDetailPhotoItem = itemView.findViewById(R.id.vehicleDetailPhotoItem);
    }
}
