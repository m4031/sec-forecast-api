package com.marsinfotech.secforecast.persistence.db.converter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastDateEntity;
import org.springframework.stereotype.Component;

@Component
public class ForecastDateEntityToForecastDateConverter extends AbstractDataConverter<ForecastDateEntity, ForecastDate> {

    public ForecastDateEntityToForecastDateConverter(){
        super(ForecastDateEntity.class, ForecastDate.class);
        init();
    }

    @Override
    public ForecastDate createOutput(ForecastDateEntity input) {
        return new ForecastDate();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ForecastDateEntity::getDate, ForecastDate::setDate);
            map(ForecastDateEntity::getForecastDates, ForecastDate::setForecastDates);
            map(ForecastDateEntity::getLastUpdateUser, ForecastDate::setLastUpdateUser);
            map(ForecastDateEntity::getLastUpdateTime, ForecastDate::setLastUpdateTime);
            setInitialized(true);
        }

	}
}
