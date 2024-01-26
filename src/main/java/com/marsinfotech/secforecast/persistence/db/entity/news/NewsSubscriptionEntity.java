package com.marsinfotech.secforecast.persistence.db.entity.news;

import com.marsinfotech.secforecast.persistence.db.entity.DatabaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "news_subscription", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Data
public class NewsSubscriptionEntity extends DatabaseEntity {

    @Column(name = "subscription_id", nullable = false)
    private String subscriptionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;
}
