package ru.lm.itunesplayer.view.adapter;

import ru.lm.itunesplayer.model.pojo.ItunesMedia;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public interface OnMediaSelected {
    void onItemClicked(ItunesMedia itunesMedia);
}
