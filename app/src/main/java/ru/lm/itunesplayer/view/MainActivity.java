package ru.lm.itunesplayer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import ru.lm.itunesplayer.R;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initFragments();
        }
    }

    private void initFragments() {
        FragmentMedia fragmentMedia = new FragmentMedia();
        fm.beginTransaction()
                .add(fragmentMedia, "fr")
                .replace(R.id.frameLayout, fragmentMedia)
                .commit();
    }
}
