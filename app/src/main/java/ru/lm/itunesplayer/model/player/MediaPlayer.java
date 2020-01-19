package ru.lm.itunesplayer.model.player;

import com.google.android.exoplayer2.SimpleExoPlayer;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public interface MediaPlayer {

    void playMedia(ItunesMedia media);

    void release();

    SimpleExoPlayer getPlayer();

    void stop();
}
