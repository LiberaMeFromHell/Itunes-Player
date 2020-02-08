package ru.lm.itunesplayer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import ru.lm.itunesplayer.R;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class MainActivity extends AppCompatActivity implements PlayerFragmentRunner {

    private FragmentManager fm = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initMediaFragments();
        }
    }

    private void initMediaFragments() {
        FragmentMedia fragmentMedia = new FragmentMedia();
        fm.beginTransaction()
                .add(fragmentMedia, "fr")
                .replace(R.id.frameLayout, fragmentMedia)
                .commit();
    }

    public void initPlayerFragment() {
        FragmentPlayer fragmentPlayer = new FragmentPlayer();
        fm.beginTransaction()
                .addToBackStack("fr2")
                .replace(R.id.frameLayout, fragmentPlayer)
                .commit();
    }
}
