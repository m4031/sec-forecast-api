package com.marsinfotech.secforecast.services.news;

import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.services.core.AbstractService;
import com.marsinfotech.secforecast.services.forecast.ForecastDateService;
import com.marsinfotech.secforecast.services.persistence.PersistenceServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NewsSubscriptionService extends AbstractService implements NewsSubscriptionServiceI {

    private final static String SERVICE_NAME = "NewsSubscriptionService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateService.class);

    @Autowired
    private PersistenceServiceI persistenceService;

    public NewsSubscriptionService() {
        super(SERVICE_NAME);
    }


    @Override
    public NewsSubscription getSubscription(String email) {
        return persistenceService.getSubscription(email);
    }

    @Override
    public List<NewsSubscription> getSubscriptions(String name) {
        return persistenceService.getSubscriptions(name);
    }

    @Override
    public NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId) {
        return persistenceService.getSubscriptionBySubscriptionId(subscriptionId);
    }

    @Override
    public NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return persistenceService.insertOrUpdate(subscription, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(String email) {
        return persistenceService.delete(email);
    }
}
