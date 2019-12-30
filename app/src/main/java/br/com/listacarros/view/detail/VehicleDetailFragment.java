package br.com.listacarros.view.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.listacarros.R;
import br.com.listacarros.model.Vehicle;
import br.com.listacarros.presenter.IListaCarros;
import br.com.listacarros.presenter.VehicleDetailsPresenter;
import br.com.listacarros.rx.RxBus;
import br.com.listacarros.utils.MoneyFormatterUtils;
import br.com.listacarros.view.BaseFragment;
import br.com.listacarros.view.activity.IToolbarListener;
import br.com.listacarros.view.detail.adapter.VehicleDetailListener;
import br.com.listacarros.view.detail.adapter.VehicleEquipmentAdapter;
import br.com.listacarros.view.detail.adapter.VehiclePhotoAdapter;
import br.com.listacarros.view.detail.adapter.VehiclePhotoViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VehicleDetailFragment extends BaseFragment implements VehicleDetailListener, IListaCarros.IViewVehicleDetail {


    private Context mContext;
    private RecyclerView mVehiclePhotoRecycler;
    private RecyclerView mVehicleEquipamentRecycler;
    private VehiclePhotoAdapter mVehiclePhotoAdapter;
    private VehicleEquipmentAdapter mVehicleEquipmentAdapter;
    private TextView mVehiCleDetailMake;
    private TextView mVehiCleDetailTrim;
    private TextView mVehiCleDetailPrice;
    private TextView mVehiCleDetailManuFactYear;
    private TextView mVehiCleDetailColor;
    private TextView mVehiCleDetailDoors;
    private TextView mVehiCleDetailFuel;
    private TextView mVehiCleDetailGear;
    private IToolbarListener mIToolbarListener;
    private VehicleDetailsPresenter mVehicleDetailsPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setmIToolbarListener(IToolbarListener mIToolbarListener) {
        this.mIToolbarListener = mIToolbarListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vehicle_detail, container, false);
        mVehiclePhotoRecycler = view.findViewById(R.id.vehicleDetailPhotoRecycler);
        mVehicleEquipamentRecycler = view.findViewById(R.id.vehicleDetailEquipamentRecycler);
        mVehiCleDetailMake = view.findViewById(R.id.vehicleDetailMakeTextView);
        mVehiCleDetailTrim = view.findViewById(R.id.vehicleDetailTrimTextView);
        mVehiCleDetailPrice = view.findViewById(R.id.vehicleDetailPriceTextView);
        mVehiCleDetailManuFactYear = view.findViewById(R.id.vehicleDetailManufactYearTextView);
        mVehiCleDetailColor = view.findViewById(R.id.vehicleDetailColorTextView);
        mVehiCleDetailDoors = view.findViewById(R.id.vehicleDetailDoorsTextView);
        mVehiCleDetailFuel = view.findViewById(R.id.vehicleDetailFuelTextView);
        mVehiCleDetailGear = view.findViewById(R.id.vehicleDetailGearTextView);
        setupToolbar();

        mVehicleDetailsPresenter = new VehicleDetailsPresenter( mContext,this);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RxBus.getInstance().listen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if(o != null) {
                        Vehicle vehicle = (Vehicle) o;
                        mVehicleDetailsPresenter.setVehicleDetailInfo(vehicle);
                    }
                });
    }

    private void setupToolbar(){
        mIToolbarListener.setToolbarTitle(mContext.getResources().getString(R.string.vehicle_detail_title));
        mIToolbarListener.setToolbarIcon(mContext.getResources().getDrawable(R.drawable.ic_arrow_back));
        mIToolbarListener.setBackClickListener();
    }

    @Override
    public void loadImage(String url, VehiclePhotoViewHolder holder) {
        mVehicleDetailsPresenter.loadImage(url, holder.mVehicleDetailPhotoItem);
    }

    @Override
    public void onSetVehicleDetailInfo(Vehicle vehicle) {
        mVehiCleDetailMake.setText(vehicle.getMake());
        mVehiCleDetailTrim.setText(vehicle.getTrim());
        mVehiCleDetailPrice.setText(String.valueOf(MoneyFormatterUtils.convertToMoney(vehicle.getPrice())));
        mVehiCleDetailManuFactYear.setText(String.valueOf(vehicle.getManufactureYear()));
        mVehiCleDetailColor.setText(vehicle.getColor());
        mVehiCleDetailDoors.setText(String.valueOf(vehicle.getDoors()));
        mVehiCleDetailGear.setText(vehicle.getGear());
        mVehiCleDetailFuel.setText(vehicle.getFuel());
    }

    @Override
    public void onSetVehicleEquipmentInfo(Vehicle vehicle) {
        mVehicleEquipmentAdapter = new VehicleEquipmentAdapter(mContext, vehicle.getEquipment());
        mVehicleEquipamentRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mVehicleEquipamentRecycler.setAdapter(mVehicleEquipmentAdapter);
    }

    @Override
    public void onSetVehiclePhoto(Vehicle vehicle) {
        mVehiclePhotoAdapter = new VehiclePhotoAdapter(mContext, vehicle.getPhotos(), this);
        mVehiclePhotoRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mVehiclePhotoRecycler.setAdapter(mVehiclePhotoAdapter);
    }

    @Override
    public void onLoadImage() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
