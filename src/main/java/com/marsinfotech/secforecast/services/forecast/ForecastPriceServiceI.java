package com.marsinfotech.secforecast.services.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastPrice;

import java.time.ZonedDateTime;
import java.util.List;

public interface ForecastPriceServiceI {

    List<ForecastPrice> getForecastPrices(int date);

    List<ForecastPrice> getForecastPrices(int date, String algo);

    List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos);

    List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                               ZonedDateTime lastUpdateTime, boolean isUpdateOneByOne);

    boolean deleteForecastPrices(int date);

    boolean deleteForecastPrices(int date, String algo);

    boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos);
}
