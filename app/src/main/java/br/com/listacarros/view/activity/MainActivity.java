package br.com.listacarros.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import br.com.listacarros.R;
import br.com.listacarros.presenter.IListaCarros;
import br.com.listacarros.presenter.MainActivityPresenter;
import br.com.listacarros.view.detail.VehicleDetailFragment;
import br.com.listacarros.view.vehicleList.VehicleListFragment;

public class MainActivity extends AppCompatActivity implements IListaCarros.IViewMainActivity, IToolbarListener {

    private MainActivityPresenter mMainActivityPresenter;
    private FragmentManager mFragmentManager;
    private VehicleListFragment mVehicleListFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mMainActivityPresenter = new MainActivityPresenter(this, mFragmentManager, this);
        mVehicleListFragment = new VehicleListFragment();
        mMainActivityPresenter.showFirstScreen(mVehicleListFragment);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment instanceof VehicleDetailFragment){
            VehicleDetailFragment vehicleDetailFragment = (VehicleDetailFragment) fragment;
            vehicleDetailFragment.setmIToolbarListener(this);
            return;
        }
        if(fragment instanceof VehicleListFragment){
            VehicleListFragment vehicleListFragment = (VehicleListFragment) fragment;
            vehicleListFragment.setmIToolbarListener(this);
            return;
        }
    }

    @Override
    public void onShowFirstScreen() {

    }

    private View.OnClickListener backClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mFragmentManager.popBackStack();
        }
    };

    @Override
    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setToolbarIcon(Drawable icon) {
        mToolbar.setNavigationIcon(icon);
    }

    @Override
    public void setBackClickListener() {
        mToolbar.setNavigationOnClickListener(backClickListener);
    }

}
