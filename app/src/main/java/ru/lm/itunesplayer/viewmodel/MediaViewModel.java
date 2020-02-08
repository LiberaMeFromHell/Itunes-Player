package ru.lm.itunesplayer.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import javax.inject.Inject;

import ru.lm.itunesplayer.model.network.MediaProvider;
import ru.lm.itunesplayer.model.network.api.NetworkReceiver;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;
import ru.lm.itunesplayer.model.transferdata.TransferData;
import ru.lm.itunesplayer.module.App;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class MediaViewModel extends AndroidViewModel {

    @Inject
    MediaProvider mediaProvider;

    @Inject
    TransferData itunesTransferData;

    public MediaViewModel(@NonNull Application application) {
        super(application);
        mediaProvider = App.getMediaComponent().injectMediaProvider();
        itunesTransferData = App.getMediaComponent().injectTransferData();
    }

    public void getItunesMediaList(String searchKeyword) {
        mediaProvider.onObserveMediaLiveData(searchKeyword);
    }

    public MutableLiveData<List<ItunesMedia>> getMediaLiveData() {
        return mediaProvider.getMediaLiveData();
    }

    public void updateTransferData(ItunesMedia itunesMedia) {
        itunesTransferData.updateTransferData(itunesMedia);
    }

    public MutableLiveData<ItunesMedia> onObserverTransferData() {
        return itunesTransferData.onObserverTransferData();
    }
}
