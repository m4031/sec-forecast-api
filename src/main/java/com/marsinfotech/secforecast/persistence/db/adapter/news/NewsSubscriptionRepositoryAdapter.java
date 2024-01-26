package com.marsinfotech.secforecast.persistence.db.adapter.news;

import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.persistence.db.converter.news.NewsSubscriptionEntityToNewsSubscriptionConverter;
import com.marsinfotech.secforecast.persistence.db.converter.news.NewsSubscriptionToNewsSubscriptionEntityConverter;
import com.marsinfotech.secforecast.persistence.db.entity.news.NewsSubscriptionEntity;
import com.marsinfotech.secforecast.persistence.db.repository.news.NewsSubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsSubscriptionRepositoryAdapter {

    private final static Logger logger = LoggerFactory.getLogger(NewsSubscriptionRepositoryAdapter.class);

    @Autowired
    private NewsSubscriptionRepository repository ;

    @Autowired
    private NewsSubscriptionEntityToNewsSubscriptionConverter dataConverter;

    @Autowired
    private NewsSubscriptionToNewsSubscriptionEntityConverter entityConverter;

    public NewsSubscription getSubscription(String email){
        NewsSubscriptionEntity entity = repository.findByEmail(email);
        if (entity != null) {
            return dataConverter.convert(entity);
        }
        return null;
    }

    public List<NewsSubscription> getSubscriptions(String name){
        List<NewsSubscription> contactList = new ArrayList<>();
        List<NewsSubscriptionEntity> entities = repository.findByName(name);
        if(!entities.isEmpty()){
            for (NewsSubscriptionEntity entity : entities) {
                contactList.add(dataConverter.convert(entity));
            }
        }
        return contactList;
    }

    public NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId){
        NewsSubscriptionEntity entity = repository.findBySubscriptionId(subscriptionId);
        if (entity != null) {
            return dataConverter.convert(entity);
        }
        return null;
    }

    public NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime){
        try{
            NewsSubscriptionEntity entity = repository.findByEmail(subscription.getEmail());
            if(entity == null){  // INSERT
                entity = entityConverter.convert(subscription);
            }else{  // UPDATE
                entity.setName(subscription.getName());
            }
            entity.setLastUpdateUser(lastUpdateUser);
            entity.setLastUpdateTime(lastUpdateTime);
            repository.save(entity);
            return dataConverter.convert(entity);
        }catch (Exception ex){
            logger.error("Error inserting/updating News subscription contactMessage={} - error={1}", subscription, ex);
        }
        return null;
    }

    public boolean delete(String email){
        try {
            NewsSubscriptionEntity entity = repository.findByEmail(email);
            if (entity == null) {
                return false;
            }
            repository.delete(entity);
        }catch (Exception ex){
            logger.error("Error deleting News subscription for email={} - error={1}", email, ex);
            return false;
        }
        return true;
    }

}
