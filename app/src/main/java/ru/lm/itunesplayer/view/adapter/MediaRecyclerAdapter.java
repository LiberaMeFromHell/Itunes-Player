package ru.lm.itunesplayer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.lm.itunesplayer.R;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class MediaRecyclerAdapter extends RecyclerView.Adapter<MediaRecyclerAdapter.MediaViewHolder> {

    private List<ItunesMedia> mediaList;
    private OnMediaSelected onMediaSelected;

    public MediaRecyclerAdapter(List<ItunesMedia> mediaList, OnMediaSelected onMediaSelected) {
        this.mediaList = mediaList;
        this.onMediaSelected = onMediaSelected;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_media ,parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, final int position) {

        holder.getItemLayout().setOnClickListener(v -> onMediaSelected.onItemClicked(mediaList.get(position)));
        holder.getArtistTextView().setText(mediaList.get(position).getArtistName());
        holder.getTitleTextView().setText(mediaList.get(position).getTrackName());
        Glide.with(holder.getAlbumImageView())
                .load(mediaList.get(position).getArtworkUrl100())
                .into(holder.getAlbumImageView());
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemLayout) View itemLayout;
        @BindView(R.id.artist) TextView artistTextView;
        @BindView(R.id.title) TextView titleTextView;
        @BindView(R.id.album) ImageView albumImageView;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public View getItemLayout() {
            return itemLayout;
        }

        public TextView getArtistTextView() {
            return artistTextView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public ImageView getAlbumImageView() {
            return albumImageView;
        }
    }
}
