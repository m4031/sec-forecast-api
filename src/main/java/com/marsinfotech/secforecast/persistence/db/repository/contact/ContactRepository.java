package com.marsinfotech.secforecast.persistence.db.repository.contact;

import com.marsinfotech.secforecast.persistence.db.entity.contact.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    @Query("SELECT DISTINCT ct.date FROM ContactEntity as ct ORDER BY ct.date DESC")
    List<Integer> findUniqueDates();

    List<ContactEntity> findByDate(int date);

    List<ContactEntity> findByEmail(String email);

    List<ContactEntity> findByEmailAndDate(String email, int date);

    ContactEntity findByContactId(String contactId);
}
