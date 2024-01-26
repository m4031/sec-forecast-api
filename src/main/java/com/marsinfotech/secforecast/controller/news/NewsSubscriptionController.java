package com.marsinfotech.secforecast.controller.news;

import com.marsinfotech.secforecast.controller.forecast.ForecastDateController;
import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.services.news.NewsSubscriptionServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Random;

@RestController
@RequestMapping(path = AppConstants.STOCKS_REQUEST_BASE_URL)
public class NewsSubscriptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateController.class);

	private String lastUpdateUser = "ABC";
	@Autowired
	private NewsSubscriptionServiceI newsSubscriptionService;

	@PostMapping(AppConstants.NEWS_SUBSCRIPTION)
	public ResponseEntity<Object> doNewsSubscription(@RequestBody NewsSubscription newsSubscription) {
		LOGGER.info("Received doNewsSubscription request");
		try {
			newsSubscription.setSubscriptionId(getServiceReqNumber());
			NewsSubscription updatedNewsSubscription = newsSubscriptionService.insertOrUpdate(newsSubscription, lastUpdateUser, ZonedDateTime.now());
			if (updatedNewsSubscription != null) {
				return new ResponseEntity<Object>(updatedNewsSubscription, new HttpHeaders(), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Failed to do News Subscription", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			LOGGER.error("Error doing News Subscription, error={0}", ex);
			return new ResponseEntity<Object>("Error while doing News Subscription", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

	public String getServiceReqNumber() {
	    Random random = new Random();
	    int ranNum = random.nextInt(999999999 - 9999) + 9999;
	    return "SR"+ranNum;
	}
}
