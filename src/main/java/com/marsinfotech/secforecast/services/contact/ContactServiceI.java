package com.marsinfotech.secforecast.services.contact;

import com.marsinfotech.secforecast.data.contact.Contact;

import java.time.ZonedDateTime;
import java.util.List;

public interface ContactServiceI {

    Contact getContact(String contactId);

    List<Contact> getContacts(String email);
    List<Contact> getContacts(String email, int date);

    Contact updateContact(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean deleteContact(String contactId);
}
