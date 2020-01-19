package ru.lm.itunesplayer.model.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.lm.itunesplayer.model.pojo.ItunesResponse;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Singleton
public class ItunesMediaReceiver implements NetworkReceiver {

    private ItunesAPI itunesAPI;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<ItunesMedia>> mediaLiveData = new MutableLiveData<>();

    @Inject
    public ItunesMediaReceiver() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        itunesAPI = retrofit.create(ItunesAPI.class);
    }

    @Override
    public void onObserveMediaLiveData(String searchKeyword) {
        disposable.add(
                itunesAPI.getItunesResponse(searchKeyword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ItunesResponse>() {
                            @Override
                            public void onNext(ItunesResponse itunesResponse) {
                                mediaLiveData.postValue(itunesResponse.getResults());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("tag", "ItunesMediaReceiver onNext: " + e);

                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );
    }

    @Override
    public MutableLiveData<List<ItunesMedia>> getMediaLiveData() {
        return mediaLiveData;
    }

    @Override
    public void dispose() {
        disposable.dispose();
    }
}
