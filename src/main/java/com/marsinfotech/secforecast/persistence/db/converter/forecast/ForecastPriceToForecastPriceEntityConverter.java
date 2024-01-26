package com.marsinfotech.secforecast.persistence.db.converter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastPriceEntity;
import org.springframework.stereotype.Component;

@Component
public class ForecastPriceToForecastPriceEntityConverter extends AbstractDataConverter<ForecastPrice, ForecastPriceEntity> {

    public ForecastPriceToForecastPriceEntityConverter(){
        super(ForecastPrice.class, ForecastPriceEntity.class);
        init();
    }

    @Override
    public ForecastPriceEntity createOutput(ForecastPrice input) {
        return new ForecastPriceEntity();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ForecastPrice::getDate, ForecastPriceEntity::setDate);
            map(ForecastPrice::getTicker, ForecastPriceEntity::setTicker);
            map(ForecastPrice::getAlgo, ForecastPriceEntity::setAlgo);
            map(ForecastPrice::getCountry, ForecastPriceEntity::setCountry);
            map(ForecastPrice::getCurrency, ForecastPriceEntity::setCurrency);
            map(ForecastPrice::getClosePrice, ForecastPriceEntity::setClosePrice);
            map(ForecastPrice::getForecastPrices, ForecastPriceEntity::setForecastPrices);
            map(ForecastPrice::getMarketTrend, ForecastPriceEntity::setMarketTrend);
            map(ForecastPrice::getForecastTrend, ForecastPriceEntity::setForecastTrend);
            map(ForecastPrice::getMarketSentiment, ForecastPriceEntity::setMarketSentiment);
            map(ForecastPrice::getLastUpdateUser, ForecastPriceEntity::setLastUpdateUser);
            map(ForecastPrice::getLastUpdateTime, ForecastPriceEntity::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
