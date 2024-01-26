package com.marsinfotech.secforecast.data;

public enum MarketSentimentType {
    UP("UP"), DOWN("DOWN"), NEUTRAL("NEUTRAL");

    private String name;

    private MarketSentimentType (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
