package com.marsinfotech.secforecast.controller.forecast;

import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.services.forecast.ForecastPriceServiceI;
import com.marsinfotech.secforecast.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = AppConstants.STOCKS_REQUEST_BASE_URL)
public class ForecastPriceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastPriceController.class);

    @Autowired
    private ForecastPriceServiceI forecastPriceService;

    private String lastUpdateUser = "ABC";

    @GetMapping(path = AppConstants.GET_FORECAST_PRICES)
    public ResponseEntity<Object> getForecastPrices(@RequestParam int date, @RequestParam(required = false) String tickers,
                                                    @RequestParam(required = false) String algos){
        LOGGER.info("Received getForecastPrice request date={}, tickers={}, algos={}", date, tickers, algos);
        try {
            List<String> tickerList = !AppUtils.isNullOrEmpty(tickers) ? Arrays.asList(tickers.split(",")) : null;
            List<String> algoList = !AppUtils.isNullOrEmpty(algos) ? Arrays.asList(algos.split(",")) : null;
            List<ForecastPrice> forecastPrices = forecastPriceService.getForecastPrices(date, tickerList, algoList);
            return new ResponseEntity<Object>(forecastPrices, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Error retrieving Forecast price for date={}, tickers={}, algos={}", date,tickers, algos, ex);
            return new ResponseEntity<Object>("Error retrieving Forecast prices", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path = AppConstants.UPDATE_FORECAST_PRICES)
    public ResponseEntity<Object> updateForecastPrices(@RequestBody List<ForecastPrice> forecastPrices,
                                                       @RequestParam boolean isUpdateOneByOne){
        LOGGER.info("Received updateForecastPrice request forecastPrices={}, isUpdateOneByOne={}", forecastPrices, isUpdateOneByOne);
        try {
            List<ForecastPrice> updatedForecastPrices = forecastPriceService.updateOrInsertForecastPrices(forecastPrices,
                    lastUpdateUser, ZonedDateTime.now(), isUpdateOneByOne);
            String message = AppUtils.isNullOrEmpty(updatedForecastPrices) ? "Failed to update" : "Successfully updated";
            message += " forecast price";
            return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Error updating Forecast price={}, error={1}", forecastPrices, ex);
            return new ResponseEntity<Object>("Error updating Forecast prices", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path = AppConstants.DELETE_FORECAST_PRICES)
    public ResponseEntity<Object> deleteForecastPrices(@RequestParam int date, @RequestParam(required = false) String tickers,
                                                       @RequestParam(required = false) String algos){
        LOGGER.info("Received deleteForecastPrice request date={}, tickers={}, algos={}", date, tickers, algos);
        try {
            List<String> tickerList = !AppUtils.isNullOrEmpty(tickers) ? Arrays.asList(tickers.split(",")) : null;
            List<String> algoList = !AppUtils.isNullOrEmpty(algos) ? Arrays.asList(algos.split(",")) : null;
            boolean isDeleted = forecastPriceService.deleteForecastPrices(date, tickerList, algoList);
            String message = isDeleted ? "Successfully deleted" : "Failed to delete";
            message += " forecast price for date=" + date + " tickers=" + tickers;
            return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
        }catch (Exception ex) {
            LOGGER.error("Error deleting Forecast price for date={}, tickers={}, error={1}", date, tickers, ex);
            return new ResponseEntity<Object>("Error deleting Forecast prices", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }
}
