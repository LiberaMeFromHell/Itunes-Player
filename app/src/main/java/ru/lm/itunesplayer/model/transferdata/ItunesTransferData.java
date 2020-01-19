package ru.lm.itunesplayer.model.transferdata;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Singleton
public class ItunesTransferData implements TransferData {

    private MutableLiveData<ItunesMedia> transferData = new MutableLiveData<>();

    @Inject
    public ItunesTransferData() {
    }

    public void updateTransferData(ItunesMedia itunesMedia) {
        transferData.postValue(itunesMedia);
    }

    public MutableLiveData<ItunesMedia> onObserverTransferData() {
        return transferData;
    }
}
