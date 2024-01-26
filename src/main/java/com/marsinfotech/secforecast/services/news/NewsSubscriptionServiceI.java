package com.marsinfotech.secforecast.services.news;

import com.marsinfotech.secforecast.data.news.NewsSubscription;

import java.time.ZonedDateTime;
import java.util.List;

public interface NewsSubscriptionServiceI {

    NewsSubscription getSubscription(String email);
    List<NewsSubscription> getSubscriptions(String name);
    NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId);

    NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean delete(String email);
}
