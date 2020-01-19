package ru.lm.itunesplayer.model.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Singleton
public class ExoPlayer implements MediaPlayer {

    private Context context;
    private SimpleExoPlayer player;

    @Inject
    public ExoPlayer(Context context) {
        this.context = context;
        player = new SimpleExoPlayer.Builder(context).build();
        player.setPlayWhenReady(true);
    }

    @Override
    public void playMedia(ItunesMedia media) {

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Itunes Player"));

        MediaSource mediaSource = new ProgressiveMediaSource
                .Factory(dataSourceFactory, new DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(media.getPreviewUrl()));

        player.prepare(mediaSource);
    }

    @Override
    public void release() {
        player.release();
    }

    @Override
    public SimpleExoPlayer getPlayer() {
        return player;
    }

    @Override
    public void stop() {
        player.stop();
    }
}
