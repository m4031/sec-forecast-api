package com.marsinfotech.secforecast.data.forecast;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ForecastDate {
    private int date;
    private int[] forecastDates;
    private String lastUpdateUser;
    private ZonedDateTime lastUpdateTime;
}
