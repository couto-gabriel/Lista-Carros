package br.com.listacarros.webClient;

import android.content.Context;

import java.util.List;

import br.com.listacarros.model.Vehicle;
import br.com.listacarros.retrofit.RetrofitBuilder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WebClient {

    private static WebClient mWebClientInstance = null;

    public static WebClient getInstance(){
        if(mWebClientInstance == null){
            mWebClientInstance = new WebClient();
        }
        return mWebClientInstance;
    }

    private RetrofitBuilder mRetrofitBuilder = new RetrofitBuilder();

    public Observable<List<Vehicle>> getDeals(){

        return mRetrofitBuilder.getDealService().getDeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
