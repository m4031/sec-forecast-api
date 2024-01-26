package com.marsinfotech.secforecast.services.contact;

import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.services.core.AbstractService;
import com.marsinfotech.secforecast.services.core.ServiceState;
import com.marsinfotech.secforecast.services.forecast.ForecastDateService;
import com.marsinfotech.secforecast.services.persistence.PersistenceServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ContactService extends AbstractService implements ContactServiceI {

    private final static String SERVICE_NAME = "ContactService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateService.class);

    @Autowired
    private PersistenceServiceI persistenceService;

    public ContactService() {
        super(SERVICE_NAME);
    }

    @Override
    public void initialize() {
        changeState(ServiceState.INITIALIZING);
        changeState(ServiceState.INITIALIZED);
    }

    @Override
    public Contact getContact(String contactId) {
        return persistenceService.getContact(contactId);
    }

    @Override
    public List<Contact> getContacts(String email) {
        return persistenceService.getContacts(email);
    }

    @Override
    public List<Contact> getContacts(String email, int date) {
        return persistenceService.getContacts(email, date);
    }

    @Override
    public Contact updateContact(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return persistenceService.updateContact(contact, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteContact(String contactId) {
        return persistenceService.deleteContact(contactId);
    }
}
