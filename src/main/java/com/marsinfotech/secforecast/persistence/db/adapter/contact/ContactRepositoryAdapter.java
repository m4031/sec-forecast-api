package com.marsinfotech.secforecast.persistence.db.adapter.contact;

import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.persistence.db.converter.contact.ContactEntityToContactConverter;
import com.marsinfotech.secforecast.persistence.db.converter.contact.ContactToContactEntityConverter;
import com.marsinfotech.secforecast.persistence.db.entity.contact.ContactEntity;
import com.marsinfotech.secforecast.persistence.db.repository.contact.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ContactRepositoryAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ContactRepositoryAdapter.class);

    @Autowired
    private ContactRepository repository ;

    @Autowired
    private ContactEntityToContactConverter dataConverter;

    @Autowired
    private ContactToContactEntityConverter entityConverter;

    public List<Integer> getContactDates(){
        List<Integer> dates = repository.findUniqueDates();
        logger.info("Unique contact dates={}", dates);
        return dates;
    }

    public List<Contact> getContactMessages(String email){
        List<Contact> contactList = new ArrayList<>();
        List<ContactEntity> entities = repository.findByEmail(email);
        if(!entities.isEmpty()){
            for (ContactEntity entity : entities) {
                contactList.add(dataConverter.convert(entity));
            }
        }
        return contactList;
    }

    public List<Contact> getContactMessages(String email, int date){
        List<Contact> contactList = new ArrayList<>();
        List<ContactEntity> entities = repository.findByEmailAndDate(email, date);
        if(!entities.isEmpty()){
            for (ContactEntity entity : entities) {
                contactList.add(dataConverter.convert(entity));
            }
        }
        return contactList;
    }

    public Contact getContactMessage(String contactId){
        ContactEntity entity = repository.findByContactId(contactId);
        if(entity == null){
            return null;
        }
        return dataConverter.convert(entity);
    }

    public Contact insertOrUpdate(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime){
        try{
            ContactEntity entity = repository.findByContactId(contact.getContactId());
            if(entity == null){  // INSERT
                entity = entityConverter.convert(contact);
            }else{  // UPDATE
                entity.setEmail(contact.getEmail());
                entity.setSubject(contact.getSubject());
                entity.setMessage(contact.getMessage());
            }
            entity.setLastUpdateUser(lastUpdateUser);
            entity.setLastUpdateTime(lastUpdateTime);
            repository.save(entity);
            return dataConverter.convert(entity);
        }catch (Exception ex){
            logger.error("Error inserting/updating contact message contactMessage={} - error={1}", contact, ex);
        }
        return null;
    }

    public boolean delete(String contactId){
        try {
            ContactEntity entity = repository.findByContactId(contactId);
            if (entity == null) {
                return false;
            }
            repository.delete(entity);
        }catch (Exception ex){
            logger.error("Error deleting contact message for contactId={} - error={1}", contactId, ex);
            return false;
        }
        return true;
    }

}
