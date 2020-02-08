package ru.lm.itunesplayer.model.network.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.lm.itunesplayer.model.pojo.ItunesResponse;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public interface ItunesAPI {
    @GET("/search")
    Observable<ItunesResponse> getItunesResponse(@Query("term") String SearchKeyword);
}
