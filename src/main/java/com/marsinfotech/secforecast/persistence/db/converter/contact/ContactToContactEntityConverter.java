package com.marsinfotech.secforecast.persistence.db.converter.contact;

import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.persistence.db.converter.AbstractDataConverter;
import com.marsinfotech.secforecast.persistence.db.entity.contact.ContactEntity;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactEntityConverter extends AbstractDataConverter<Contact, ContactEntity> {

    public ContactToContactEntityConverter(){
        super(Contact.class, ContactEntity.class);
        init();
    }

    @Override
    public ContactEntity createOutput(Contact input) {
        return new ContactEntity();
    }

    @Override
    public void init() {
        if (!isInitialized()) {
            map(Contact::getDate, ContactEntity::setDate);
            map(Contact::getContactId, ContactEntity::setContactId);
            map(Contact::getName, ContactEntity::setName);
            map(Contact::getEmail, ContactEntity::setEmail);
            map(Contact::getSubject, ContactEntity::setSubject);
            map(Contact::getMessage, ContactEntity::setMessage);
            map(Contact::getLastUpdateUser, ContactEntity::setLastUpdateUser);
            map(Contact::getLastUpdateTime, ContactEntity::setLastUpdateTime);
            setInitialized(true);
        }
    }
}
