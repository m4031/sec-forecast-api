package com.marsinfotech.secforecast.persistence.db.repository.forecast;

import com.marsinfotech.secforecast.persistence.db.entity.forecast.ForecastDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastDateRepository extends JpaRepository<ForecastDateEntity, Long> {

	ForecastDateEntity findByDate(int date);

	ForecastDateEntity findFirstByOrderByDateDesc();

	@Query("SELECT fd.date FROM ForecastDateEntity as fd ORDER BY fd.date DESC")
	List<Integer> findForecastCalculationDates();

}
