package ru.lm.itunesplayer.model.network;

import dagger.Module;
import dagger.Provides;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Module
public class NetworkModule {

    @Provides
    NetworkReceiver networkReceiver(ItunesMediaReceiver receiver) {
        return receiver;
    }
}
