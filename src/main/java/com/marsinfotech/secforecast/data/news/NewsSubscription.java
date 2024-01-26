package com.marsinfotech.secforecast.data.news;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class NewsSubscription {

    private String subscriptionId;
    private String name;
    private String email;
    private String lastUpdateUser;
    private ZonedDateTime lastUpdateTime;

}
