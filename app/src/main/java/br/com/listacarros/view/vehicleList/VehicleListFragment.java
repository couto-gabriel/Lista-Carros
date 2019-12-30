package br.com.listacarros.view.vehicleList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import br.com.listacarros.R;
import br.com.listacarros.model.Vehicle;
import br.com.listacarros.presenter.IListaCarros;
import br.com.listacarros.presenter.VehicleListPresenter;
import br.com.listacarros.rx.RxBus;
import br.com.listacarros.utils.LoadImageUtils;
import br.com.listacarros.view.BaseFragment;
import br.com.listacarros.view.activity.IToolbarListener;
import br.com.listacarros.view.detail.VehicleDetailFragment;
import br.com.listacarros.view.vehicleList.adapter.IVehicleItemAdapterListener;
import br.com.listacarros.view.vehicleList.adapter.VehicleListAdapter;
import br.com.listacarros.view.vehicleList.adapter.VehicleViewHolder;

public class VehicleListFragment extends BaseFragment implements IListaCarros.IViewVehicleList, IVehicleItemAdapterListener {

    private VehicleListAdapter mVehicleAdapter;
    private Context mContext;
    private VehicleListPresenter mVehicleListPresenter;
    private ProgressBar mProgressbar;
    private ConstraintLayout mErrorContainer;
    private Button mRetryRequestButton;
    private RecyclerView mRecycler;
    private TextView mTotalVehiclesTextView;
    private SwipeRefreshLayout mVehicleListSwipeRefresh;
    private FragmentManager mFragmentManager;
    private ConstraintLayout mVehicleListContainer;
    private IToolbarListener mIToolbarListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mIsFirstRequest = true ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setmIToolbarListener(IToolbarListener mIToolbarListener) {
        this.mIToolbarListener = mIToolbarListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
        mVehicleListPresenter = new VehicleListPresenter(mContext, this, mFragmentManager);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vehicle_list, container, false);
        mProgressbar = view.findViewById(R.id.vehicleList_progressBar);
        mProgressbar.setIndeterminate(true);
        mErrorContainer = view.findViewById(R.id.errorContainerVehicleList);
        mRetryRequestButton = view.findViewById(R.id.vehicleListButtonRetry);
        mRecycler = view.findViewById(R.id.vehicleListRecycler);
        mTotalVehiclesTextView = view.findViewById(R.id.vehicleListTotalVehiclesTextView);
        mVehicleListSwipeRefresh = view.findViewById(R.id.vehicleListSwipeRefresh);
        mVehicleListContainer = view.findViewById(R.id.listContainerVehiclelist);
        setupToolbar();

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager = mLinearLayoutManager;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRetryRequestButton.setOnClickListener(onRetryClick);
        mVehicleListSwipeRefresh.setOnRefreshListener(refreshListener);

        mVehicleListPresenter.checkFirstRequest(mIsFirstRequest);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVehicleListPresenter.checkFirstRequest(mIsFirstRequest);

    }

    @Override
    public void onCheckFirstRequest(boolean isFirstRequest) {
        mIsFirstRequest = isFirstRequest;

    }

    private void setupToolbar(){
        mIToolbarListener.setToolbarTitle(mContext.getResources().getString(R.string.app_name));
        mIToolbarListener.setToolbarIcon(null);
    }

    private View.OnClickListener onRetryClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mVehicleListPresenter.hideErrorContainer();
            mVehicleListPresenter.showProgress();
            mVehicleListPresenter.requestVehicleList();
        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mVehicleListPresenter.showProgress();
            mVehicleListPresenter.requestVehicleList();
            mVehicleListContainer.setVisibility(View.GONE);
            mTotalVehiclesTextView.setVisibility(View.GONE);
        }
    };


    @Override
    public View.OnClickListener OnItemClickListener(Vehicle vehicle) {
        return v -> {
            RxBus.getInstance().send(vehicle);
            VehicleDetailFragment vehicleDetailFragment = new VehicleDetailFragment();
            mVehicleListPresenter.goToVehicleDetails(vehicleDetailFragment);
        };
    }

    @Override
    public void loadImage(List<String> photosUrl, VehicleViewHolder viewHolder) {

        mVehicleListPresenter.requestVehicleImage(photosUrl, viewHolder);
    }

    @Override
    public void onRequestVehicleImage(String photoUrl, VehicleViewHolder viewHolder) {

        LoadImageUtils.load(mContext, photoUrl, viewHolder.mVehicleItemImageView);
    }

    @Override
    public void onShowProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onShowErrorContainer() {
        mErrorContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideErrorContainer() {
        mErrorContainer.setVisibility(View.GONE);
    }

    @Override
    public void onRequestVehicleListError(String exception) {
        mProgressbar.setVisibility(View.GONE);
        mVehicleListSwipeRefresh.setRefreshing(false);
        Log.d("GABRIEL", "Exception " + exception);
        mVehicleListPresenter.hideProgress();
        mVehicleListPresenter.showErrorContainer();
    }

    @Override
    public void onRequestVehicleList(List<Vehicle> list) {
        mProgressbar.setVisibility(View.GONE);
        mVehicleListSwipeRefresh.setRefreshing(false);
        mVehicleListPresenter.hideProgress();
        mVehicleAdapter = new VehicleListAdapter(mContext, list, this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mVehicleAdapter);
        mVehicleListContainer.setVisibility(View.VISIBLE);
        mTotalVehiclesTextView.setVisibility(View.VISIBLE);
        mTotalVehiclesTextView.setText(list.size() + " " + mContext.getResources().getString(R.string.total_vehicles_result));

        mVehicleListPresenter.updateListToDatabase(list);

    }


    @Override
    public void onGoToVehicleDetails() {
    }

    @Override
    public void onUpdateListToDatabase() {
        Log.d("GABRIEL", "LIST UPDATED TO DATABASE");

    }

    @Override
    public void onFetchDataFromDatabase(List<Vehicle> vehicles) {
        Log.d("GABRIEL", "FETCHED FROM DATABASE");
        Log.d("GABRIEL", "FETCHED FROM DATABASE");

        mProgressbar.setVisibility(View.GONE);
        mVehicleListSwipeRefresh.setRefreshing(false);
        mVehicleListPresenter.hideProgress();
        mVehicleAdapter = new VehicleListAdapter(mContext, vehicles, this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mVehicleAdapter);
        mVehicleListContainer.setVisibility(View.VISIBLE);
        mTotalVehiclesTextView.setVisibility(View.VISIBLE);
        mTotalVehiclesTextView.setText(vehicles.size() + " " + mContext.getResources().getString(R.string.total_vehicles_result));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVehicleListPresenter.disposeWhenDestroy();
    }
}
