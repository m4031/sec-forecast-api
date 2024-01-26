package com.marsinfotech.secforecast.services.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.services.core.AbstractService;
import com.marsinfotech.secforecast.services.core.ServiceState;
import com.marsinfotech.secforecast.services.persistence.PersistenceServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ForecastPriceService extends AbstractService implements ForecastPriceServiceI {

    private final static String SERVICE_NAME = "ForecastPriceService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastPriceService.class);

    @Autowired
    private PersistenceServiceI persistenceService;

    public ForecastPriceService() {
        super(SERVICE_NAME);
    }

    @Override
    public void initialize() {
        changeState(ServiceState.INITIALIZING);
        changeState(ServiceState.INITIALIZED);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date) {
        return persistenceService.getForecastPrices(date);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, String algo) {
        return persistenceService.getForecastPrices(date, algo);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos) {
        return persistenceService.getForecastPrices(date, tickers, algos);
    }

    @Override
    public List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                                           ZonedDateTime lastUpdateTime, boolean isUpdateOneByOne) {
        return persistenceService.updateOrInsertForecastPrices(forecastPrices, lastUpdateUser, lastUpdateTime, isUpdateOneByOne);
    }

    @Override
    public boolean deleteForecastPrices(int date) {
        return persistenceService.deleteForecastPrices(date);
    }



    @Override
    public boolean deleteForecastPrices(int date, String algo) {
        return persistenceService.deleteForecastPrices(date, algo);
    }

    @Override
    public boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos) {
        return persistenceService.deleteForecastPrices(date, tickers, algos);
    }

}
