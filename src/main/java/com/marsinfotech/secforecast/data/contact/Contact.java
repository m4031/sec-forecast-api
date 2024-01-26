package com.marsinfotech.secforecast.data.contact;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class Contact {

	private int date;
	private String contactId;
	private String name;
	private String email;
//	private String phone;
	private String subject;
	private String message;
	private String lastUpdateUser;
	private ZonedDateTime lastUpdateTime;
}
