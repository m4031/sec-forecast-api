package com.marsinfotech.secforecast.data;

public enum MarketTrendType {
    UP("UP"), DOWN("DOWN"), NEUTRAL("NEUTRAL");

    private String name;

    private MarketTrendType (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
