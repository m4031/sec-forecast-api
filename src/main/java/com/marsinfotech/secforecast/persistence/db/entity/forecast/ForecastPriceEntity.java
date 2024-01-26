package com.marsinfotech.secforecast.persistence.db.entity.forecast;

import com.marsinfotech.secforecast.data.ForecastTrendType;
import com.marsinfotech.secforecast.data.MarketSentimentType;
import com.marsinfotech.secforecast.data.MarketTrendType;
import com.marsinfotech.secforecast.persistence.db.entity.DatabaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "forecast_price")
@Data
public class ForecastPriceEntity extends DatabaseEntity {

    @Column(name="date", nullable = false)
    private int date;

    @Column(name="ticker", nullable = false)
    private String ticker;

    @Column(name="algo", nullable = false)
    private String algo;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="currency", nullable = false)
    private String currency;

    @Column(name="close_price", nullable = false)
    private double closePrice;

    @Column(name="forecast_prices", nullable = false)
    private double[] forecastPrices;

    @Enumerated(EnumType.STRING)
    @Column(name="market_trend", nullable = false)
    private MarketTrendType marketTrend;

    @Enumerated(EnumType.STRING)
    @Column(name="forecast_trend", nullable = false)
    private ForecastTrendType forecastTrend;

    @Enumerated(EnumType.STRING)
    @Column(name="market_sentiment", nullable = false)
    private MarketSentimentType marketSentiment;
}