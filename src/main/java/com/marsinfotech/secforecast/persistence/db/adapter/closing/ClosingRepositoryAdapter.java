package com.marsinfotech.secforecast.persistence.db.adapter.closing;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.persistence.db.converter.closing.ClosingEntityToClosingConverter;
import com.marsinfotech.secforecast.persistence.db.converter.closing.ClosingToClosingEntityConverter;
import com.marsinfotech.secforecast.persistence.db.entity.closing.ClosingEntity;
import com.marsinfotech.secforecast.persistence.db.repository.closing.ClosingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClosingRepositoryAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ClosingRepositoryAdapter.class);

    @Autowired
    private ClosingRepository repository ;

    @Autowired
    private ClosingEntityToClosingConverter dataConverter;

    @Autowired
    private ClosingToClosingEntityConverter entityConverter;


    public List<Closing> getClosings(int date){
        List<Closing> closings = new ArrayList();
        List<ClosingEntity> entities = repository.findByDate(date);
        if(entities != null || !entities.isEmpty()){
            for (ClosingEntity entity : entities) {
                closings.add(dataConverter.convert(entity));
            }
        }
        return closings;
    }

    public Closing getClosing(int date, String ticker){
        ClosingEntity entity = repository.findByDateAndTicker(date, ticker);
        return dataConverter.convert(entity);
    }

    public boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime){
        try{
            ClosingEntity entity = repository.findByDateAndTicker(closing.getDate(), closing.getTicker());
            if(entity == null){  // INSERT
                entity = entityConverter.convert(closing);
            }else{  // UPDATE
                entity.setOpenPrice(closing.getOpenPrice());
                entity.setHighestPrice(closing.getHighestPrice());
                entity.setLowestPrice(closing.getLowestPrice());
                entity.setClosePrice(closing.getClosePrice());
                entity.setHighestVolume(closing.getAdjustedClosePrice());
                entity.setLowestVolume(closing.getVolume());
            }
            entity.setLastUpdateUser(lastUpdateUser);
            entity.setLastUpdateTime(lastUpdateTime);
            repository.save(entity);
        }catch (Exception ex){
            logger.error("Error inserting/updating closing info closing={} - error={1}", closing, ex);
            return false;
        }
        return true;
    }

    public boolean delete(int date, String ticker){
        try {
            ClosingEntity entity = repository.findByDateAndTicker(date, ticker);
            if (entity == null) {
                return false;
            }
            repository.delete(entity);
        }catch (Exception ex){
            logger.error("Error deleting closing info for date={}, ticker={} - error={1}", date, ticker, ex);
            return false;
        }
        return true;
    }
}
