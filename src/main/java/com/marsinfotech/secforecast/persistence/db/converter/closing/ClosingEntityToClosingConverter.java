package com.marsinfotech.secforecast.persistence.db.converter.closing;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.closing.ClosingEntity;
import org.springframework.stereotype.Component;

@Component
public class ClosingEntityToClosingConverter extends AbstractDataConverter<ClosingEntity, Closing> {

    public ClosingEntityToClosingConverter(){
        super(ClosingEntity.class, Closing.class);
        init();
    }

    @Override
    public Closing createOutput(ClosingEntity input) {
        return new Closing();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ClosingEntity::getDate, Closing::setDate);
            map(ClosingEntity::getTicker, Closing::setTicker);
            map(ClosingEntity::getCountry, Closing::setCountry);
            map(ClosingEntity::getCurrency, Closing::setCurrency);
            map(ClosingEntity::getOpenPrice, Closing::setOpenPrice);
            map(ClosingEntity::getHighestPrice, Closing::setHighestPrice);
            map(ClosingEntity::getLowestPrice, Closing::setLowestPrice);
            map(ClosingEntity::getClosePrice, Closing::setClosePrice);
            map(ClosingEntity::getHighestVolume, Closing::setAdjustedClosePrice);
            map(ClosingEntity::getLastUpdateUser, Closing::setLastUpdateUser);
            map(ClosingEntity::getLastUpdateTime, Closing::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
