package com.marsinfotech.secforecast.persistence.db.converter.closing;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.closing.ClosingEntity;
import org.springframework.stereotype.Component;

@Component
public class ClosingToClosingEntityConverter extends AbstractDataConverter<Closing, ClosingEntity> {

    public ClosingToClosingEntityConverter() {
        super(Closing.class, ClosingEntity.class);
        init();
    }

    @Override
    public ClosingEntity createOutput(Closing input) {
        return new ClosingEntity();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(Closing::getDate, ClosingEntity::setDate);
            map(Closing::getTicker, ClosingEntity::setTicker);
            map(Closing::getCountry, ClosingEntity::setCountry);
            map(Closing::getCurrency, ClosingEntity::setCurrency);
            map(Closing::getOpenPrice, ClosingEntity::setOpenPrice);
            map(Closing::getHighestPrice, ClosingEntity::setHighestPrice);
            map(Closing::getLowestPrice, ClosingEntity::setLowestPrice);
            map(Closing::getClosePrice, ClosingEntity::setClosePrice);
            map(Closing::getAdjustedClosePrice, ClosingEntity::setHighestVolume);
            map(Closing::getLastUpdateUser, ClosingEntity::setLastUpdateUser);
            map(Closing::getLastUpdateTime, ClosingEntity::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
