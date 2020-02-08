package ru.lm.itunesplayer.module;

import javax.inject.Singleton;

import dagger.Component;
import ru.lm.itunesplayer.model.connectivitystatus.ConnectivityStatus;
import ru.lm.itunesplayer.model.network.MediaProvider;
import ru.lm.itunesplayer.model.network.MediaProviderModule;
import ru.lm.itunesplayer.model.network.api.ItunesAPI;
import ru.lm.itunesplayer.model.network.api.NetworkReceiver;
import ru.lm.itunesplayer.model.network.api.NetworkReceiverModule;
import ru.lm.itunesplayer.model.player.MediaPlayer;
import ru.lm.itunesplayer.model.player.PlayerModule;
import ru.lm.itunesplayer.model.transferdata.TransferData;
import ru.lm.itunesplayer.model.transferdata.TransferDataModule;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Component(modules = {
        NetworkReceiverModule.class,
        PlayerModule.class,
        AppContextModule.class,
        TransferDataModule.class,
        MediaProviderModule.class})

@Singleton
public interface MediaComponent {

    ItunesAPI injectItunesAPI();
    MediaPlayer injectMediaPlayer();
    TransferData injectTransferData();
    ConnectivityStatus injectConnectivityStatus();
    MediaProvider injectMediaProvider();
}
