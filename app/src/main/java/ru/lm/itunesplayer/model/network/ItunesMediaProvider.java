package ru.lm.itunesplayer.model.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ru.lm.itunesplayer.model.network.api.ItunesAPI;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;
import ru.lm.itunesplayer.model.pojo.ItunesResponse;
import ru.lm.itunesplayer.module.App;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 08.02.2020
 */

public class ItunesMediaProvider implements MediaProvider {

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<ItunesMedia>> mediaLiveData = new MutableLiveData<>();

    @Inject
    ItunesAPI itunesAPI;

    @Inject
    public ItunesMediaProvider() {
        App.getMediaComponent().injectItunesAPI();
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
                                Log.d("tag", "ItunesNetworkReceiver onNext: " + e);

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
