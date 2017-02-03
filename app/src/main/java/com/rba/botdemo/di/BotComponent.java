package com.rba.botdemo.di;

import com.rba.botdemo.api.NetworkModule;
import com.rba.botdemo.synchronize.SynchronizeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

@Singleton
@Component(modules = NetworkModule.class)

public interface BotComponent {

    void injectSynchronize(SynchronizeActivity synchronizeActivity);

}
