package com.marsinfotech.secforecast.persistence.db.entity.forecast;

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
//@Table(name = "forecast_dates", /*schema = "dbo"*/, uniqueConstraints = @UniqueConstraint(columnNames = {"date"}))
@Table(name = "forecast_dates", uniqueConstraints = @UniqueConstraint(columnNames = {"date"}))
@Data
public class ForecastDateEntity extends DatabaseEntity {

    @Column(name="date", nullable = false, unique = true)
    private int date;

    @Column(name="forecast_dates", nullable = false)
    private int[] forecastDates;
}