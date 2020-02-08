package ru.lm.itunesplayer.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import ru.lm.itunesplayer.R;
import ru.lm.itunesplayer.model.connectivitystatus.ConnectivityStatus;
import ru.lm.itunesplayer.model.pojo.ItunesMedia;
import ru.lm.itunesplayer.module.App;
import ru.lm.itunesplayer.view.adapter.MediaRecyclerAdapter;
import ru.lm.itunesplayer.view.adapter.OnMediaSelected;
import ru.lm.itunesplayer.viewmodel.MediaViewModel;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class FragmentMedia extends Fragment implements OnMediaSelected {

    private static final int MINIMUM_QUERY_LENGTH = 5;
    private static final int QUERY_TIMEOUT = 300;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.mediaLayout) View mediaLayout;

    private MediaRecyclerAdapter recyclerAdapter;
    private MediaViewModel viewModel;
    private List<ItunesMedia> mediaList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        ButterKnife.bind(this, view);
        ConnectivityStatus connectivityStatus = App.getMediaComponent().connectivityStatus();
        initRecyclerView();

        viewModel = ViewModelProviders.of(this).get(MediaViewModel.class);
        viewModel.getMediaLiveData().observe(this, itunesMedia -> {
            mediaList.clear();
            mediaList.addAll(itunesMedia);
            recyclerAdapter.notifyDataSetChanged();
        });
        Log.d("tag", "onCreateView: " + connectivityStatus.hasConnection());
        if (!connectivityStatus.hasConnection()) {
            Log.d("tag", "Snackbar");
            Snackbar.make(mediaLayout, "No internet connection", Snackbar.LENGTH_LONG)
            .show();
        }
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerAdapter = new MediaRecyclerAdapter(mediaList, this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClicked(ItunesMedia itunesMedia) {
        viewModel.updateTransferData(itunesMedia);
        runPlayer();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.length() >= MINIMUM_QUERY_LENGTH) {
                        emitter.onNext(newText);
                    }
                    return false;
                }
            });
        }).map(s -> s.trim())
                .debounce(QUERY_TIMEOUT, TimeUnit.MILLISECONDS)
                .subscribe(s -> viewModel.getItunesMediaList(s));
    }

    private void runPlayer() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentPlayer fragmentPlayer = new FragmentPlayer();
        fm.beginTransaction()
                .addToBackStack("fr2")
                .replace(R.id.frameLayout, fragmentPlayer)
                .commit();
    }
}
