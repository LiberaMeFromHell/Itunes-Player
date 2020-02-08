package ru.lm.itunesplayer.model.network;

import dagger.Module;
import dagger.Provides;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 08.02.2020
 */

@Module
public class MediaProviderModule {
    @Provides
    MediaProvider mediaProvider(ItunesMediaProvider mediaProvider) {
        return mediaProvider;
    }
}
