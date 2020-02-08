package ru.lm.itunesplayer.model.network.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Singleton
public class ItunesNetworkReceiver implements NetworkReceiver {

    private ItunesAPI itunesAPI;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<ItunesMedia>> mediaLiveData = new MutableLiveData<>();

    @Inject
    public ItunesNetworkReceiver() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        itunesAPI = retrofit.create(ItunesAPI.class);
    }

    public ItunesAPI getItunesAPI() {
        return itunesAPI;
    }
}
