package com.marsinfotech.secforecast.persistence.db.converter.news;

import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.news.NewsSubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsSubscriptionEntityToNewsSubscriptionConverter extends AbstractDataConverter<NewsSubscriptionEntity, NewsSubscription> {

    public NewsSubscriptionEntityToNewsSubscriptionConverter(){
        super(NewsSubscriptionEntity.class, NewsSubscription.class);
        init();
    }

    @Override
    public NewsSubscription createOutput(NewsSubscriptionEntity input) {
        return new NewsSubscription();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(NewsSubscriptionEntity::getSubscriptionId, NewsSubscription::setSubscriptionId);
            map(NewsSubscriptionEntity::getName, NewsSubscription::setName);
            map(NewsSubscriptionEntity::getEmail, NewsSubscription::setEmail);
            map(NewsSubscriptionEntity::getLastUpdateUser, NewsSubscription::setLastUpdateUser);
            map(NewsSubscriptionEntity::getLastUpdateTime, NewsSubscription::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
