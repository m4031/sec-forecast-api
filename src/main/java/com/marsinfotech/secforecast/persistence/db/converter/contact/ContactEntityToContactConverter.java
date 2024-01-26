package com.marsinfotech.secforecast.persistence.db.converter.contact;

import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.contact.ContactEntity;
import org.springframework.stereotype.Component;

@Component
public class ContactEntityToContactConverter extends AbstractDataConverter<ContactEntity, Contact> {

    public ContactEntityToContactConverter(){
        super(ContactEntity.class, Contact.class);
        init();
    }

    @Override
    public Contact createOutput(ContactEntity input) {
        return new Contact();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(ContactEntity::getDate, Contact::setDate);
            map(ContactEntity::getContactId, Contact::setContactId);
            map(ContactEntity::getName, Contact::setName);
            map(ContactEntity::getEmail, Contact::setEmail);
            map(ContactEntity::getSubject, Contact::setSubject);
            map(ContactEntity::getMessage, Contact::setMessage);
            map(ContactEntity::getLastUpdateUser, Contact::setLastUpdateUser);
            map(ContactEntity::getLastUpdateTime, Contact::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
