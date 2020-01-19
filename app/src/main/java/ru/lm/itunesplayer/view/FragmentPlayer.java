package ru.lm.itunesplayer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.PlayerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.lm.itunesplayer.R;
import ru.lm.itunesplayer.model.player.MediaPlayer;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;
import ru.lm.itunesplayer.module.App;
import ru.lm.itunesplayer.viewmodel.MediaViewModel;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class FragmentPlayer extends Fragment {

    @BindView(R.id.exoPlayer) PlayerView playerView;
    @BindView(R.id.album) ImageView albumImageView;
    @BindView(R.id.title) TextView titleTextView;
    @BindView(R.id.artist) TextView artistTextView;

    @Inject
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);
        MediaViewModel viewModel = ViewModelProviders.of(this).get(MediaViewModel.class);
        viewModel.onObserverTransferData().observe(this, this::play);
        mediaPlayer = App.getMediaComponent().injectMediaPlayer();
        playerView.setPlayer(mediaPlayer.getPlayer());
        return view;
    }

    private void play(ItunesMedia media) {

        artistTextView.setText(media.getArtistName());
        titleTextView.setText(media.getTrackName());
        Glide.with(albumImageView)
                .load(media.getArtworkUrl100())
                .into(albumImageView);
        mediaPlayer.playMedia(media);
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
