package com.marsinfotech.secforecast.controller.contact;

import java.time.ZonedDateTime;
import java.util.Random;
import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.services.contact.ContactServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AppConstants.STOCKS_REQUEST_BASE_URL)
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	private String lastUpdateUser = "ABC";
	@Autowired
	private ContactServiceI contactService;

	@PostMapping(AppConstants.CONTACT_US)
	public ResponseEntity<Object> saveContactInquiryDetails(@RequestBody Contact contact) {
		LOGGER.info("Received getCalculationDates request");
		try {
//			contact.setDate();
			contact.setContactId(getServiceReqNumber());
			Contact updatedContact = contactService.updateContact(contact, lastUpdateUser, ZonedDateTime.now());
			if (updatedContact != null) {
				return new ResponseEntity<Object>(updatedContact, new HttpHeaders(), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Failed to save contact message", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			LOGGER.error("Error retrieving Forecast calculation dates, error={0}", ex);
			return new ResponseEntity<Object>("Error while saving contact message", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

	public String getServiceReqNumber() {
	    Random random = new Random();
	    int ranNum = random.nextInt(999999999 - 9999) + 9999;
	    return "SR"+ranNum;
	}
}
