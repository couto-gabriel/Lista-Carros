package br.com.listacarros.view.detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.listacarros.R;

public class VehiclePhotoAdapter extends RecyclerView.Adapter {

    private List<String> mPhotoList;
    private Context mContext;
    private VehicleDetailListener mIVehicleDetailListener;

    public VehiclePhotoAdapter(Context context, List<String> list, VehicleDetailListener IVehicleDetailListener){
        mContext = context;
        mPhotoList = list;
        mIVehicleDetailListener = IVehicleDetailListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_detail_photo, parent, false);

        return new VehiclePhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            String url = mPhotoList.get(position);
            VehiclePhotoViewHolder detailViewHolder = (VehiclePhotoViewHolder) holder;
            mIVehicleDetailListener.loadImage(url, detailViewHolder);

    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}
