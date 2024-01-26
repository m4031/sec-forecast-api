package com.marsinfotech.secforecast.controller.forecast;

import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.services.forecast.ForecastDateServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = AppConstants.STOCKS_REQUEST_BASE_URL)
public class ForecastDateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateController.class);

    @Autowired
    private ForecastDateServiceI forecastDateService;
    private String lastUpdateUser = "ABC";

    @GetMapping(path = AppConstants.GET_CALCULATION_DATES)
    public ResponseEntity<Object>  getCalculationDates() {
        LOGGER.info("Received getCalculationDates request");
        try {
            List<Integer> dates = forecastDateService.getCalculationDates();
            return new ResponseEntity<Object>(dates, new HttpHeaders(), HttpStatus.OK);
        }catch (Exception ex) {
            LOGGER.error("Error retrieving Forecast calculation dates, error={0}", ex);
            return new ResponseEntity<Object>("Error retrieving forecast calculation dates", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path = AppConstants.GET_FORECAST_DATE)
    public ResponseEntity<Object> getForecastDate(@RequestParam int date){
        LOGGER.info("Received getForecastDate request date={}", date);
        try {
            ForecastDate forecastDate = forecastDateService.getForecastDate(date);
            return new ResponseEntity<Object>(forecastDate, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Error retrieving Forecast date for date={}", date, ex);
            return new ResponseEntity<Object>("Error retrieving Forecast date", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path = AppConstants.UPDATE_FORECAST_DATE)
    public ResponseEntity<Object> updateOrInsertForecastDate(@RequestBody ForecastDate forecastDate){
        LOGGER.info("Received updateOrInsertForecastDate request forecastDate={}", forecastDate);
        try {
            boolean isUpdated = forecastDateService.updateOrInsertForecastDate(forecastDate, lastUpdateUser, ZonedDateTime.now());
            String message = isUpdated ? "Successfully updated" : "Failed to update";
            message += " forecast date";
            return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Error updating Forecast date={}, error={1}", forecastDate, ex);
            return new ResponseEntity<Object>("Error updating Forecast date", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path = AppConstants.DELETE_FORECAST_DATE)
    public ResponseEntity<Object> deleteForecastDate(@RequestParam int date) {
        LOGGER.info("Received deleteForecastDate request date={}", date);
        try {
            boolean isDeleted = forecastDateService.deleteForecastDate(date);
            String message = isDeleted ? "Successfully deleted" : "Failed to delete";
            message += " forecast date for date=" + date;
            return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
        }catch (Exception ex) {
            LOGGER.error("Error deleting Forecast date for date={}, error={1}", date, ex);
            return new ResponseEntity<Object>("Error deleting Forecast date", new HttpHeaders(),
                    HttpStatus.FORBIDDEN);
        }
    }
}
