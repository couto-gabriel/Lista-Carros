package br.com.listacarros.rx;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class RxBus {

    private static RxBus mRxBusInstance = null;

    public static RxBus getInstance(){
        if(mRxBusInstance == null){
            mRxBusInstance = new RxBus();
        }
        return mRxBusInstance;
    }

    private BehaviorSubject<Object> bus = BehaviorSubject.create();

        public void send(Object o) {
            bus.onNext(o);
        }

        public Observable<Object> listen() {
            return bus;
        }
}
