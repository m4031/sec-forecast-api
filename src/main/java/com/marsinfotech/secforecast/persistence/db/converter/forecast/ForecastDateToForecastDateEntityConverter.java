package com.marsinfotech.secforecast.persistence.db.converter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastDateEntity;
import org.springframework.stereotype.Component;

@Component
public class ForecastDateToForecastDateEntityConverter extends AbstractDataConverter<ForecastDate, ForecastDateEntity> {

    public ForecastDateToForecastDateEntityConverter(){
        super(ForecastDate.class, ForecastDateEntity.class);
        init();
    }

    @Override
    public ForecastDateEntity createOutput(ForecastDate input) {
        return new ForecastDateEntity();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ForecastDate::getDate, ForecastDateEntity::setDate);
            map(ForecastDate::getForecastDates, ForecastDateEntity::setForecastDates);
            map(ForecastDate::getLastUpdateUser, ForecastDateEntity::setLastUpdateUser);
            map(ForecastDate::getLastUpdateTime, ForecastDateEntity::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
