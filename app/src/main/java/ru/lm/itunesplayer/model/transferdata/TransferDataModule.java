package ru.lm.itunesplayer.model.transferdata;

import dagger.Module;
import dagger.Provides;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

@Module
public class TransferDataModule {

    @Provides
    TransferData transferData(ItunesTransferData transferData) {
        return transferData;
    }
}
