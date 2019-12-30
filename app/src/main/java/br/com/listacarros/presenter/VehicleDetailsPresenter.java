package br.com.listacarros.presenter;


import android.content.Context;
import android.widget.ImageView;

import br.com.listacarros.model.Vehicle;
import br.com.listacarros.utils.LoadImageUtils;
import br.com.listacarros.view.detail.adapter.VehiclePhotoViewHolder;

public class VehicleDetailsPresenter implements IListaCarros.IPresenterVehicleDetail {

    private IListaCarros.IViewVehicleDetail mIViewVehicleDetail;
    private Context mContext;

    public VehicleDetailsPresenter(Context context, IListaCarros.IViewVehicleDetail IViewVehicleDetail){
        mIViewVehicleDetail = IViewVehicleDetail;
        mContext = context;
    }

    @Override
    public void setVehicleDetailInfo(Vehicle vehicle) {
        if(vehicle != null){
            mIViewVehicleDetail.onSetVehicleDetailInfo(vehicle);

            if(vehicle.getPhotos() != null){
                mIViewVehicleDetail.onSetVehiclePhoto(vehicle);
            }

            if(vehicle.getEquipment() != null){
                mIViewVehicleDetail.onSetVehicleEquipmentInfo(vehicle);
            }
        }
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        LoadImageUtils.load(mContext, url, imageView);
        mIViewVehicleDetail.onLoadImage();
    }
}
