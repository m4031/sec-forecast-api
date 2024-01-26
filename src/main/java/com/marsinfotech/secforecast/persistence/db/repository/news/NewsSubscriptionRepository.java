package com.marsinfotech.secforecast.persistence.db.repository.news;

import com.marsinfotech.secforecast.persistence.db.entity.news.NewsSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsSubscriptionRepository extends JpaRepository<NewsSubscriptionEntity, Long> {

    NewsSubscriptionEntity findByEmail(String email);

    NewsSubscriptionEntity findBySubscriptionId(String subscriptionId);
    List<NewsSubscriptionEntity> findByName(String name);

}
