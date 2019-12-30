package br.com.listacarros.retrofit;

import br.com.listacarros.service.DealService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private Retrofit mRetrofit;

    public RetrofitBuilder(){
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://private-525ce1-icarrostest.apiary-mock.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public DealService getDealService(){
        return mRetrofit.create(DealService.class);
    }
}
