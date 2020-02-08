package ru.lm.itunesplayer.model.network.api;

import dagger.Module;
import dagger.Provides;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Module
public class NetworkReceiverModule {

    @Provides
    ItunesAPI networkReceiver(ItunesNetworkReceiver receiver) {
        return receiver.getItunesAPI();
    }
}
