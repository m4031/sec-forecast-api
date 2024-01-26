package com.marsinfotech.secforecast.data;

public enum ForecastTrendType {
    UP("UP"), DOWN("DOWN"), NEUTRAL("NEUTRAL");

    private String name;

    private ForecastTrendType (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
