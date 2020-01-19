package ru.lm.itunesplayer.model.player;

import dagger.Module;
import dagger.Provides;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Module
public class PlayerModule {
    @Provides
    MediaPlayer mediaPlayer(ExoPlayer player) {
        return player;
    }
}
