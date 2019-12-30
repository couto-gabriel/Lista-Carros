package br.com.listacarros.view.vehicleList.adapter;

import android.view.View;

import java.util.List;

import br.com.listacarros.model.Vehicle;

public interface IVehicleItemAdapterListener {

    View.OnClickListener OnItemClickListener(Vehicle vehicle);
    void loadImage(List<String> photos, VehicleViewHolder viewHolder);
}
