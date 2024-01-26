package com.marsinfotech.secforecast.persistence.db.converter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastPriceEntity;
import org.springframework.stereotype.Component;

@Component
public class ForecastPriceEntityToForecastPriceConverter extends AbstractDataConverter<ForecastPriceEntity, ForecastPrice> {

    public ForecastPriceEntityToForecastPriceConverter(){
        super(ForecastPriceEntity.class, ForecastPrice.class);
        init();
    }

    @Override
    public ForecastPrice createOutput(ForecastPriceEntity input) {
        return new ForecastPrice();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ForecastPriceEntity::getDate, ForecastPrice::setDate);
            map(ForecastPriceEntity::getTicker, ForecastPrice::setTicker);
            map(ForecastPriceEntity::getAlgo, ForecastPrice::setAlgo);
            map(ForecastPriceEntity::getCountry, ForecastPrice::setCountry);
            map(ForecastPriceEntity::getCurrency, ForecastPrice::setCurrency);
            map(ForecastPriceEntity::getClosePrice, ForecastPrice::setClosePrice);
            map(ForecastPriceEntity::getForecastPrices, ForecastPrice::setForecastPrices);
            map(ForecastPriceEntity::getMarketTrend, ForecastPrice::setMarketTrend);
            map(ForecastPriceEntity::getForecastTrend, ForecastPrice::setForecastTrend);
            map(ForecastPriceEntity::getMarketSentiment, ForecastPrice::setMarketSentiment);
            map(ForecastPriceEntity::getLastUpdateUser, ForecastPrice::setLastUpdateUser);
            map(ForecastPriceEntity::getLastUpdateTime, ForecastPrice::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
