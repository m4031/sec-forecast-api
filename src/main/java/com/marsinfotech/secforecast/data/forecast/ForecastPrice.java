package com.marsinfotech.secforecast.data.forecast;

import com.marsinfotech.secforecast.data.ForecastTrendType;
import com.marsinfotech.secforecast.data.MarketSentimentType;
import com.marsinfotech.secforecast.data.MarketTrendType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ForecastPrice {
    private int date;
    private String ticker;
    private String algo;
    private String country;
    private String currency;
    private double closePrice;
    private double[] forecastPrices;
    private MarketTrendType marketTrend;
    private ForecastTrendType forecastTrend;
    private MarketSentimentType marketSentiment;
    private String lastUpdateUser;
    private ZonedDateTime lastUpdateTime;
}
