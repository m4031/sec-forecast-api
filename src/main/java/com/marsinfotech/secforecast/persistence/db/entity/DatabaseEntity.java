package com.marsinfotech.secforecast.persistence.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
@ToString
@EqualsAndHashCode
public class DatabaseEntity implements DatabaseEntityI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "last_update_time")
    protected ZonedDateTime lastUpdateTime;

    @Column(name = "last_update_user")
    protected String lastUpdateUser;
}
