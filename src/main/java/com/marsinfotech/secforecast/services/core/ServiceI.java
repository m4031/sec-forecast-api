package com.marsinfotech.secforecast.services.core;

public interface ServiceI {

    String getName();

    ServiceState getState();

    void initialize();

    void start();

    void preload();

    void poststart();

    void stop();

    void restart();

    boolean isEnabled();

    void setEnabled(boolean enabled);

    boolean isPreloadEnabled();

    boolean isPostloadEnabled();

    void setPreloadEnabled(boolean enabled);

    void setPostloadEnabled(boolean enabled);

    int getPostStartDelayInSecs();
}
