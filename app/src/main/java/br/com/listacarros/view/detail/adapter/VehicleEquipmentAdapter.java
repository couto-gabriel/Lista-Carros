package br.com.listacarros.view.detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.listacarros.R;
import br.com.listacarros.model.Equipment;

public class VehicleEquipmentAdapter extends RecyclerView.Adapter {

    private List<Equipment> mVehicleEquipmentList;
    private Context mContext;

    public VehicleEquipmentAdapter(Context context, List<Equipment> list){
        mVehicleEquipmentList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_detail_equipament, parent, false);

        return new VehicleEquipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Equipment equipment = mVehicleEquipmentList.get(position);

        VehicleEquipmentViewHolder equipamentViewHolder = (VehicleEquipmentViewHolder) holder;
        equipamentViewHolder.mVehicleEquipamentItemTextView.setText(equipment.getDescription());

    }

    @Override
    public int getItemCount() {
        return mVehicleEquipmentList.size();
    }
}
