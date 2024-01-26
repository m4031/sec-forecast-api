package com.marsinfotech.secforecast.persistence.db.adapter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.persistence.db.converter.forecast.ForecastDateEntityToForecastDateConverter;
import com.marsinfotech.secforecast.persistence.db.converter.forecast.ForecastDateToForecastDateEntityConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastDateEntity;
import com.marsinfotech.secforecast.persistence.db.repository.forecast.ForecastDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class ForecastDateRepositoryAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ForecastDateRepositoryAdapter.class);

    @Autowired
    private ForecastDateRepository repository ;

    @Autowired
    private ForecastDateEntityToForecastDateConverter dataConverter;

    @Autowired
    private ForecastDateToForecastDateEntityConverter entityConverter;

    public List<Integer> getCalculationDates(){
        List<Integer> dates = repository.findForecastCalculationDates();
        logger.info("All forecast calculation dates={}", dates);
        return dates;
    }

    public ForecastDate getCurrentForecastDate(){
        ForecastDateEntity forecastDateEntity = repository.findFirstByOrderByDateDesc();
        if(forecastDateEntity == null){
            return null;
        }
        return dataConverter.convert(forecastDateEntity);
    }

    public ForecastDate getForecastDate(int date){
        ForecastDateEntity entity = repository.findByDate(date);
        if(entity == null){
            return null;
        }
        return dataConverter.convert(entity);
    }

    public boolean insertOrUpdate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime){
        try{
            ForecastDateEntity entity = repository.findByDate(forecastDate.getDate());
            if(entity == null){  // INSERT
                entity = entityConverter.convert(forecastDate);
            }else{  // UPDATE
                entity.setForecastDates(forecastDate.getForecastDates());
            }
            entity.setLastUpdateUser(lastUpdateUser);
            entity.setLastUpdateTime(lastUpdateTime);
            repository.save(entity);
        }catch (Exception ex){
            logger.error("Error inserting/updating forecast price={} - error={1}", forecastDate, ex);
            return false;
        }
        return true;
    }

    public boolean delete(int date){
        try {
            ForecastDateEntity forecastDateEntity = repository.findByDate(date);
            if (forecastDateEntity == null) {
                return false;
            }
            repository.delete(forecastDateEntity);
        }catch (Exception ex){
            logger.error("Error deleting forecast date data for date={} - error={1}", date, ex);
            return false;
        }
        return true;
    }
}
