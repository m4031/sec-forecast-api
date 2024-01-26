package com.marsinfotech.secforecast.persistence.db.repository.closing;

import com.marsinfotech.secforecast.persistence.db.entity.closing.ClosingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosingRepository extends JpaRepository<ClosingEntity, Long> {

    List<ClosingEntity> findByDate(int date);
    ClosingEntity findByDateAndTicker(int date, String ticker);

}
