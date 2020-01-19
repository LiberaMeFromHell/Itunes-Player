package ru.lm.itunesplayer.module;

import javax.inject.Singleton;

import dagger.Component;
import ru.lm.itunesplayer.model.connectivitystatus.ConnectivityStatus;
import ru.lm.itunesplayer.model.network.NetworkModule;
import ru.lm.itunesplayer.model.network.NetworkReceiver;
import ru.lm.itunesplayer.model.player.MediaPlayer;
import ru.lm.itunesplayer.model.player.PlayerModule;
import ru.lm.itunesplayer.model.transferdata.TransferData;
import ru.lm.itunesplayer.model.transferdata.TransferDataModule;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Component(modules = {
        NetworkModule.class,
        PlayerModule.class,
        AppContextModule.class,
        TransferDataModule.class})

@Singleton
public interface MediaComponent {

    NetworkReceiver injectNetworkReceiver();
    MediaPlayer injectMediaPlayer();
    TransferData injectTransferData();
    ConnectivityStatus connectivityStatus();
}
