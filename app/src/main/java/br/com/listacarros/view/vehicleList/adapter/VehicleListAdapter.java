package br.com.listacarros.view.vehicleList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.listacarros.R;
import br.com.listacarros.model.Vehicle;
import br.com.listacarros.utils.MoneyFormatterUtils;

public class VehicleListAdapter extends RecyclerView.Adapter {

    private List<Vehicle> mList;
    private Context mContext;
    private IVehicleItemAdapterListener mIVehicleItemAdapterListener;

    public VehicleListAdapter(Context context, List<Vehicle> list, IVehicleItemAdapterListener mIVehicleItemAdapterListener){
        mContext = context;
        mList = list;
        this.mIVehicleItemAdapterListener = mIVehicleItemAdapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle, parent, false);

        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Vehicle vehicle = mList.get(position);

        VehicleViewHolder vehicleHolder = (VehicleViewHolder) holder;

        if(vehicle != null) {
            vehicleHolder.mVehicleItemMakeTextView.setText(vehicle.getMake());
            vehicleHolder.mVehicleItemModelTextView.setText(vehicle.getTrim());
            vehicleHolder.mVehicleItemFuelTextView.setText(vehicle.getFuel());
            vehicleHolder.mVehicleItemGearTextView.setText(vehicle.getGear());
            vehicleHolder.mVehicleItemPriceTextView.setText(String.valueOf(MoneyFormatterUtils.convertToMoney((vehicle.getPrice()))));
            vehicleHolder.mVehicleItemManuFactYearTextView.setText(String.valueOf(vehicle.getManufactureYear()) + "/");
            vehicleHolder.mVehicleItemModelYearTextView.setText(String.valueOf(vehicle.getModelYear()));
            vehicleHolder.mVehicleItemColorTextView.setText(String.valueOf(vehicle.getColor()));

            vehicleHolder.mVehicleItemContainer.setOnClickListener(mIVehicleItemAdapterListener.OnItemClickListener(vehicle));
            vehicleHolder.mVehicleItemImageView.setOnClickListener(mIVehicleItemAdapterListener.OnItemClickListener(vehicle));

            if (vehicle.getPhotos() != null) {
                mIVehicleItemAdapterListener.loadImage(vehicle.getPhotos(), vehicleHolder);
            }
        }


    }

    @Override
    public int getItemCount() {

        return mList.size();
    }
}
