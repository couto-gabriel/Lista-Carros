package br.com.listacarros.service;

import java.util.List;

import br.com.listacarros.model.Vehicle;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DealService {


    @GET("deals")
    Observable<List<Vehicle>> getDeals();

}
