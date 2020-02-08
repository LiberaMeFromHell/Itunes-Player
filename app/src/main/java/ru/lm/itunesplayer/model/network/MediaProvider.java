package ru.lm.itunesplayer.model.network;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 08.02.2020
 */

public interface MediaProvider {

    void onObserveMediaLiveData(String searchKeyword);

    MutableLiveData<List<ItunesMedia>> getMediaLiveData();

    void dispose();
}
