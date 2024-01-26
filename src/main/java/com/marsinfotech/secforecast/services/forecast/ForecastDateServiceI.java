package com.marsinfotech.secforecast.services.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface ForecastDateServiceI {

    public List<Integer> getCalculationDates();

    public ForecastDate getForecastDate(int lastForecastDate);

    public boolean updateOrInsertForecastDate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    public boolean deleteForecastDate(int date);
}
