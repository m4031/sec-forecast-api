package com.marsinfotech.secforecast.persistence.db.adapter.forecast;

import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.persistence.db.converter.forecast.ForecastPriceEntityToForecastPriceConverter;
import com.marsinfotech.secforecast.persistence.db.converter.forecast.ForecastPriceToForecastPriceEntityConverter;
import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastPriceEntity;
import com.marsinfotech.secforecast.persistence.db.repository.forecast.ForecastPriceRepository;
import com.marsinfotech.secforecast.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class ForecastPriceRepositoryAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ForecastPriceRepositoryAdapter.class);

    @Autowired
    private ForecastPriceRepository repository ;

    @Autowired
    private ForecastPriceEntityToForecastPriceConverter dataConverter;

    @Autowired
    private ForecastPriceToForecastPriceEntityConverter entityConverter;

    public List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos){
        List<ForecastPrice> forecastPrices = new ArrayList<>();
        List<ForecastPriceEntity> entities = repository.findByDate(date);
        if(entities == null || entities.isEmpty()){
            return null;
        }
        boolean isValidTicker = !AppUtils.isNullOrEmpty(tickers);
        boolean isValidAlgo = !AppUtils.isNullOrEmpty(algos);
        for (ForecastPriceEntity entity: entities) {
            if (isValidTicker && !tickers.contains(entity.getTicker())) {
                continue;
            }
            if (isValidAlgo && !algos.contains(entity.getAlgo())) {
                continue;
            }
            ForecastPrice fp = dataConverter.convert(entity);
            forecastPrices.add(fp);
        }
        return forecastPrices;
    }

    public List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                              ZonedDateTime lastUpdateTime, boolean updateOneByOne){
        if (forecastPrices == null || forecastPrices.isEmpty()) {
            return Collections.emptyList();
        }
        if (updateOneByOne) {
            // Alternative way - Retrieve, update and save one by one
            List<ForecastPrice> pricesSaved = new ArrayList<>();
            for (ForecastPrice forecastPrice: forecastPrices) {
                try{
                    ForecastPriceEntity entity = repository.findByDateAndTickerAndAlgo(forecastPrice.getDate(),
                            forecastPrice.getTicker(), forecastPrice.getAlgo());
                    if(entity == null){  // INSERT
                        entity = entityConverter.convert(forecastPrice);
                    }else{  // UPDATE
                        entity.setCountry(forecastPrice.getCountry());
                        entity.setCurrency(forecastPrice.getCurrency());
                        entity.setClosePrice(forecastPrice.getClosePrice());
                        entity.setForecastPrices(forecastPrice.getForecastPrices());
                        entity.setMarketTrend(forecastPrice.getMarketTrend());
                        entity.setForecastTrend(forecastPrice.getForecastTrend());
                        entity.setMarketSentiment(forecastPrice.getMarketSentiment());
                    }
                    entity.setLastUpdateUser(lastUpdateUser);
                    entity.setLastUpdateTime(lastUpdateTime);
                    repository.save(entity);
                    pricesSaved.add(forecastPrice);
                }catch (Exception ex){
                    logger.error("Error inserting/updating forecast price={} - error={1}", forecastPrices, ex);
                    throw ex;
                }
            }
            return pricesSaved;
        }
        List<ForecastPriceEntity> entities = repository.findByDate(forecastPrices.get(0).getDate());
        List<ForecastPriceEntity> entitiesToBeSaved = new ArrayList<>();
        try {
            for (ForecastPrice forecastPrice: forecastPrices) {
                for (ForecastPriceEntity entity: entities) {
                    if (entity.getDate() == forecastPrice.getDate() && entity.getTicker().equals(forecastPrice.getTicker())
                            && entity.getAlgo().equals(forecastPrice.getAlgo())) {
                        // UPDATE
                        entity = entityConverter.convert(forecastPrice);
                        entity.setCountry(forecastPrice.getCountry());
                        entity.setCurrency(forecastPrice.getCurrency());
                        entity.setClosePrice(forecastPrice.getClosePrice());
                        entity.setForecastPrices(forecastPrice.getForecastPrices());
                        entity.setMarketTrend(forecastPrice.getMarketTrend());
                        entity.setForecastTrend(forecastPrice.getForecastTrend());
                        entity.setMarketSentiment(forecastPrice.getMarketSentiment());
                        entity.setLastUpdateUser(lastUpdateUser);
                        entity.setLastUpdateTime(lastUpdateTime);
                    }
                    else {
                        // INSERT
                        entity = entityConverter.convert(forecastPrice);
                    }
                    entitiesToBeSaved.add(entity);
                }
            }
            repository.saveAll(entitiesToBeSaved);
        }catch (Exception ex){
            logger.error("Error inserting/updating forecast price={} - error={1}", forecastPrices, ex);
            throw ex;
        }
        return forecastPrices;
    }

    public boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos){
        try {
            List<ForecastPriceEntity> forecastPriceEntities = repository.findByDate(date);
            if (forecastPriceEntities == null || forecastPriceEntities.isEmpty()) {
                return false;
            }
            boolean isValidTicker = !AppUtils.isNullOrEmpty(tickers);
            boolean isValidAlgo = !AppUtils.isNullOrEmpty(algos);
            List<ForecastPriceEntity> toBeDeleted = new ArrayList<>();
            for (ForecastPriceEntity entity : forecastPriceEntities) {
                if (isValidTicker && isValidAlgo && tickers.contains(entity.getTicker()) &&
                        algos.contains(entity.getAlgo())) {
                    toBeDeleted.add(entity);
                } else if (isValidTicker && tickers.contains(entity.getTicker())) {
                    toBeDeleted.add(entity);
                }else if (isValidAlgo && algos.contains(entity.getAlgo())) {
                    toBeDeleted.add(entity);
                }
            }
            repository.deleteAll(toBeDeleted);
        }catch (Exception ex){
            logger.error("Error deleting forecast price for date={}, tickers={} - error={2}", date, tickers, ex);
            return false;
        }
        return true;
    }

}
