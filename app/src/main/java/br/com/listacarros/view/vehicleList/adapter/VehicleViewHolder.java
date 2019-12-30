package br.com.listacarros.view.vehicleList.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.listacarros.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder {

    public ImageView mVehicleItemImageView;
    public TextView mVehicleItemMakeTextView;
    public TextView mVehicleItemModelTextView;
    public TextView mVehicleItemPriceTextView;
    public TextView mVehicleItemColorTextView;
    public TextView mVehicleItemManuFactYearTextView;
    public TextView mVehicleItemModelYearTextView;
    public TextView mVehicleItemFuelTextView;
    public TextView mVehicleItemGearTextView;
    public CardView mVehicleItemContainer;


    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        mVehicleItemImageView = itemView.findViewById(R.id.vehicleItemImageView);
        mVehicleItemMakeTextView = itemView.findViewById(R.id.vehicleItemMakeTextView);
        mVehicleItemModelTextView = itemView.findViewById(R.id.vehicleItemModelTextView);
        mVehicleItemPriceTextView = itemView.findViewById(R.id.vehicleItemPriceTextView);
        mVehicleItemColorTextView = itemView.findViewById(R.id.vehicleItemColorTextView);
        mVehicleItemManuFactYearTextView = itemView.findViewById(R.id.vehicleItemManuFactYearTextView);
        mVehicleItemModelYearTextView = itemView.findViewById(R.id.vehicleItemModelYearTextView);
        mVehicleItemFuelTextView = itemView.findViewById(R.id.vehicleItemFuelTextView);
        mVehicleItemGearTextView = itemView.findViewById(R.id.vehicleItemGearTextView);
        mVehicleItemContainer = itemView.findViewById(R.id.vehicleItemContainer);

    }
}
