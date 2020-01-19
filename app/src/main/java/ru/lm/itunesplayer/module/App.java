package ru.lm.itunesplayer.module;

import android.app.Application;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class App extends Application {
    private static MediaComponent mediaComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaComponent = DaggerMediaComponent.
                builder().
                appContextModule(new AppContextModule(this)).
                build();
    }

    public static MediaComponent getMediaComponent() {
        return mediaComponent;
    }
}
