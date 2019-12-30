package br.com.listacarros.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.com.listacarros.R;
import br.com.listacarros.utils.FragmentUtils;

public class MainActivityPresenter implements IListaCarros.IPresenterMainActivity {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private IListaCarros.IViewMainActivity mViewMainActivity;

    public MainActivityPresenter(Context context, FragmentManager fragmentManager, IListaCarros.IViewMainActivity IViewMainActivity){
        mContext = context;
        mFragmentManager = fragmentManager;
        mViewMainActivity = IViewMainActivity;
    }

    @Override
    public void showFirstScreen(Fragment fragment) {
        FragmentTransaction tx = mFragmentManager.beginTransaction();
        tx.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        FragmentUtils.showFragment(tx, R.id.main_container, fragment);
        mViewMainActivity.onShowFirstScreen();
    }
}
