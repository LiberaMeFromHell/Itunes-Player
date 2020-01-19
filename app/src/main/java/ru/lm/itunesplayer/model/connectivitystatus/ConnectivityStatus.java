package ru.lm.itunesplayer.model.connectivitystatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Singleton
public class ConnectivityStatus {

    private Context context;
    private ConnectivityManager cm;

    @Inject
    public ConnectivityStatus(Context context) {
        this.context = context;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    public boolean hasConnection() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities nc = cm.getNetworkCapabilities(network);
                return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
            }
        } else {
            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
            for (NetworkInfo tempNetworkInfo : networkInfos) {
                if (tempNetworkInfo.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
