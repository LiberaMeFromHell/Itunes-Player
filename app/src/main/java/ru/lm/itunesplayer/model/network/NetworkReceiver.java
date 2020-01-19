package ru.lm.itunesplayer.model.network;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public interface NetworkReceiver {

    void onObserveMediaLiveData(String searchKeyword);

    MutableLiveData<List<ItunesMedia>> getMediaLiveData();

    void dispose();
}
