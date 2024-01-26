package com.marsinfotech.secforecast.services.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastDate;
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
public class ForecastDateService extends AbstractService implements ForecastDateServiceI {

    private final static String SERVICE_NAME = "ForecastDateService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateService.class);

    @Autowired
    private PersistenceServiceI persistenceService;

    public ForecastDateService() {
        super(SERVICE_NAME);
    }

    @Override
    public void initialize() {
        changeState(ServiceState.INITIALIZING);
        changeState(ServiceState.INITIALIZED);
    }

    @Override
    public List<Integer> getCalculationDates() {
        return persistenceService.getCalculationDates();
    }

    @Override
    public ForecastDate getForecastDate(int lastForecastDate) {
        return persistenceService.getForecastDate(lastForecastDate);
    }

    @Override
    public boolean updateOrInsertForecastDate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return persistenceService.updateOrInsertForecastDate(forecastDate, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteForecastDate(int date) {
        return persistenceService.deleteForecastDate(date);
    }
}
