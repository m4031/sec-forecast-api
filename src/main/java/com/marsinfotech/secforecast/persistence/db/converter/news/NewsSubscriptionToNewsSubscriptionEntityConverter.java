package com.marsinfotech.secforecast.persistence.db.converter.news;

import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.news.NewsSubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsSubscriptionToNewsSubscriptionEntityConverter extends AbstractDataConverter<NewsSubscription, NewsSubscriptionEntity> {

    public NewsSubscriptionToNewsSubscriptionEntityConverter(){
        super(NewsSubscription.class, NewsSubscriptionEntity.class);
        init();
    }

    @Override
    public NewsSubscriptionEntity createOutput(NewsSubscription input) {
        return new NewsSubscriptionEntity();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(NewsSubscription::getSubscriptionId, NewsSubscriptionEntity::setSubscriptionId);
            map(NewsSubscription::getName, NewsSubscriptionEntity::setName);
            map(NewsSubscription::getEmail, NewsSubscriptionEntity::setEmail);
            map(NewsSubscription::getLastUpdateUser, NewsSubscriptionEntity::setLastUpdateUser);
            map(NewsSubscription::getLastUpdateTime, NewsSubscriptionEntity::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
