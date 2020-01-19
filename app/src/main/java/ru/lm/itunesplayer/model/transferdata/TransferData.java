package ru.lm.itunesplayer.model.transferdata;

import androidx.lifecycle.MutableLiveData;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public interface TransferData {

    void updateTransferData(ItunesMedia itunesMedia);

    MutableLiveData<ItunesMedia> onObserverTransferData();
}
