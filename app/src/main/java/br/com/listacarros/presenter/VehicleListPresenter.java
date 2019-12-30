package br.com.listacarros.presenter;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import br.com.listacarros.R;
import br.com.listacarros.database.builder.DatabaseBuilder;
import br.com.listacarros.model.Vehicle;
import br.com.listacarros.utils.FragmentUtils;
import br.com.listacarros.view.detail.VehicleDetailFragment;
import br.com.listacarros.view.vehicleList.adapter.VehicleViewHolder;
import br.com.listacarros.webClient.WebClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class VehicleListPresenter implements IListaCarros.IPresenterVehicleList {

    private Disposable mDisposable;
    private Context mContext;
    private IListaCarros.IViewVehicleList mIViewVehicleList;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Activity mActivity;

    public VehicleListPresenter(Activity activity, Context context, IListaCarros.IViewVehicleList IvehicleList, FragmentManager fragmentManager) {
        mContext = context;
        mIViewVehicleList = IvehicleList;
        mFragmentManager = fragmentManager;
        mActivity = activity;

    }

    @Override
    public void checkFirstRequest(boolean isFirstRequest){
        if(isFirstRequest) {
            isFirstRequest = false;
            requestVehicleList();
            mIViewVehicleList.onCheckFirstRequest(isFirstRequest);
            return;
        }
        fetchDataFromDatabase();
        mIViewVehicleList.onCheckFirstRequest(isFirstRequest);
    }

    @Override
    public Parcelable saveListState(RecyclerView.LayoutManager linearLayoutManager) {
        Parcelable listState = null;
        if(linearLayoutManager != null) {
            listState = linearLayoutManager.onSaveInstanceState();
        }
        mIViewVehicleList.onSaveListState();
        return listState;
    }

    @Override
    public void restoreListState(Parcelable listState, RecyclerView.LayoutManager linearLayoutManager) {
        if(listState != null){
            if(linearLayoutManager != null){
                linearLayoutManager.onRestoreInstanceState(listState);
                mIViewVehicleList.onRestoreListState();
            }
        }
    }



    @Override
    public Parcelable getActivityRestoreInstanceState(Bundle state, String Key) {
        mIViewVehicleList.onGetActivityRestoreInstanceState();
        return state.getParcelable(Key);
    }

    @Override
    public void requestVehicleList() {
        mDisposable = WebClient.getInstance().getDeals().subscribeWith(new DisposableObserver<List<Vehicle>>(){
            @Override
            public void onNext(List<Vehicle> vehicles) {
                if(vehicles != null) {
                    if(vehicles.size() > 0) {
                        mIViewVehicleList.onRequestVehicleList(vehicles);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e.getLocalizedMessage() != null) {
                    requestVehicleListError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void requestVehicleListError(String exception) {
        mIViewVehicleList.onRequestVehicleListError(exception);
    }

    @Override
    public void requestVehicleImage(List<String> photosUrl, VehicleViewHolder viewHolder) {
        String url = null;

        if (photosUrl != null) {
            for (String photoUrl : photosUrl) {
                url = photoUrl;
                break;
            }
        }
        mIViewVehicleList.onRequestVehicleImage(url, viewHolder);
    }

    @Override
    public void showProgress() {
        mIViewVehicleList.onShowProgress();
    }

    @Override
    public void hideProgress() {
        mIViewVehicleList.onHideProgress();
    }

    @Override
    public void showErrorContainer() {
        mIViewVehicleList.onShowErrorContainer();
    }

    @Override
    public void hideErrorContainer() {
        mIViewVehicleList.onHideErrorContainer();
    }

    @Override
    public void disposeWhenDestroy() {
        if (mDisposable != null) {
            if (!mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
        }
    }

    @Override
    public void goToVehicleDetails(VehicleDetailFragment fragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_bottom);
        FragmentUtils.showFragmentAndAddBackStack(mFragmentTransaction, R.id.main_container, fragment, "fragmentVehicleDetails");
        mIViewVehicleList.onGoToVehicleDetails();

    }

    @Override
    public void updateListToDatabase(List<Vehicle> vehicles) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if(vehicles.size() == 0) {

                    for (Vehicle vehicle : DatabaseBuilder.getInstance(mContext).vehicleDao().getList()) {
                        DatabaseBuilder.getInstance(mContext).vehicleDao().insert(vehicle);
                    }
                    return;
                }
                for(Vehicle vehicle: vehicles){
                    DatabaseBuilder.getInstance(mContext).vehicleDao().update(vehicle);
                }
            }
        });


        mIViewVehicleList.onUpdateListToDatabase();
    }

    @Override
    public void fetchDataFromDatabase() {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Vehicle> vehicles = DatabaseBuilder.getInstance(mContext).vehicleDao().getList();
                if( vehicles.size() == 0){
                    requestVehicleList();
                }
                else{
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mIViewVehicleList.onFetchDataFromDatabase(vehicles);
                        }
                    });

                }
            }
        });


    }
}
