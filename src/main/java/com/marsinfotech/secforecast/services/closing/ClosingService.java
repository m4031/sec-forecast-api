package com.marsinfotech.secforecast.services.closing;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.services.core.AbstractService;
import com.marsinfotech.secforecast.services.core.ServiceState;
import com.marsinfotech.secforecast.services.forecast.ForecastDateService;
import com.marsinfotech.secforecast.services.persistence.PersistenceServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ClosingService extends AbstractService implements ClosingServiceI {

    private final static String SERVICE_NAME = "ClosingService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateService.class);

    @Autowired
    private PersistenceServiceI persistenceService;

    public ClosingService() {
        super(SERVICE_NAME);
    }

    @Override
    public void initialize() {
        changeState(ServiceState.INITIALIZING);
        changeState(ServiceState.INITIALIZED);
    }


    @Override
    public List<Closing> getClosings(int date) {
        return persistenceService.getClosings(date);
    }

    @Override
    public Closing getClosing(int date, String ticker) {
        return persistenceService.getClosing(date, ticker);
    }

    @Override
    public boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return persistenceService.insertOrUpdate(closing, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(int date, String ticker) {
        return persistenceService.delete(date, ticker);
    }
}
