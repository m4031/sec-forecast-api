package com.marsinfotech.secforecast.persistence.db.repository.forecast;

import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastPriceRepository extends JpaRepository<ForecastPriceEntity, Long> {

	List<ForecastPriceEntity> findByDate(int date);

	List<ForecastPriceEntity> findByDateAndTicker(int date, String ticker);

	List<ForecastPriceEntity> findByDateAndAlgo(int date, String algo);

	ForecastPriceEntity findByDateAndTickerAndAlgo(int date, String ticker, String algo);
}
