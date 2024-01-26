package com.marsinfotech.secforecast.services.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService implements ServiceI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    protected String name;
    private ServiceState state = ServiceState.UNITIALIZED;
    private boolean enabled = true;
    private boolean preloadEnabled = true;
    private boolean postloadEnabled = true;
    private int postStartDelayInSecs;

    public AbstractService(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ServiceState getState() {
        return state;
    }

    @Override
    public void initialize() {
        changeState(ServiceState.INITIALIZING);
        changeState(ServiceState.INITIALIZED);
    }

    @Override
    public void start() {
        changeState(ServiceState.STARTING);
        changeState(ServiceState.STARTED);
    }

    @Override
    public void preload() {
        changeState(ServiceState.PRELOADING);
        changeState(ServiceState.PRELOADED);
    }

    @Override
    public void poststart() {
        changeState(ServiceState.POSTSTARTING);
        changeState(ServiceState.POSTSTARTED);
    }

    @Override
    public void stop() {
        changeState(ServiceState.STOPPING);
        changeState(ServiceState.STOPPED);
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    protected void changeState(ServiceState newState){
        LOGGER.info("Service:{} State:{} -> {}", getName(), state, newState);
        this.state = newState;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isPreloadEnabled() {
        return preloadEnabled;
    }

    @Override
    public boolean isPostloadEnabled() {
        return postloadEnabled;
    }

    @Override
    public void setPreloadEnabled(boolean enabled) {
        this.preloadEnabled = enabled;
    }

    @Override
    public void setPostloadEnabled(boolean enabled) {
        this.postloadEnabled = enabled;
    }

    @Override
    public int getPostStartDelayInSecs() {
        return postStartDelayInSecs;
    }

    public void setPostStartDelayInSecs(int postStartDelayInSecs) {
        this.postStartDelayInSecs = postStartDelayInSecs;
    }
}
