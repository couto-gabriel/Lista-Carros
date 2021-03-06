package br.com.listacarros.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.listacarros.model.Vehicle;
import br.com.listacarros.view.detail.VehicleDetailFragment;
import br.com.listacarros.view.vehicleList.adapter.VehicleViewHolder;

public interface IListaCarros{

    interface IPresenterMainActivity{
        void showFirstScreen(Fragment fragment);
    }

    interface IViewMainActivity{
        void onShowFirstScreen();
    }

    interface IPresenterVehicleList{

        void requestVehicleList();
        void requestVehicleImage(List<String> photosUrl, VehicleViewHolder viewHolder);
        void requestVehicleListError(String exception);
        void showProgress();
        void hideProgress();
        void showErrorContainer();
        void hideErrorContainer();
        void disposeWhenDestroy();
        void goToVehicleDetails(VehicleDetailFragment fragment);
        void fetchDataFromDatabase();
        void updateListToDatabase(List<Vehicle> vehicle);
        void checkFirstRequest(boolean isFirstRequest);
        Parcelable saveListState(RecyclerView.LayoutManager linearLayoutManager);
        void restoreListState(Parcelable listState, RecyclerView.LayoutManager layoutManager);
        Parcelable getActivityRestoreInstanceState(Bundle state, String KEY);
    }

    interface IViewVehicleList{

        void onRequestVehicleList(List<Vehicle> list);
        void onRequestVehicleListError(String exception);
        void onRequestVehicleImage(String photoUrl, VehicleViewHolder viewHolder);
        void onShowProgress();
        void onHideProgress();
        void onShowErrorContainer();
        void onHideErrorContainer();
        void onGoToVehicleDetails();
        void onFetchDataFromDatabase(List<Vehicle> vehicles);
        void onUpdateListToDatabase();
        void onCheckFirstRequest(boolean isFirstRequest);
        void onSaveListState();
        void onRestoreListState();
        void onGetActivityRestoreInstanceState();
    }

    interface IPresenterVehicleDetail{
        void setVehicleDetailInfo(Vehicle vehicle);
        void loadImage(String url, ImageView imageView);
    }

    interface IViewVehicleDetail{
        void onSetVehicleDetailInfo(Vehicle vehicle);
        void onSetVehicleEquipmentInfo(Vehicle vehicle);
        void onSetVehiclePhoto(Vehicle vehicle);
        void onLoadImage();
    }
}
