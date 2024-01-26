package com.marsinfotech.secforecast.persistence.db.entity.contact;
import com.marsinfotech.secforecast.persistence.db.entity.DatabaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contact_messages")
@Data
public class ContactEntity extends DatabaseEntity {

	@Column(name = "date", nullable = false)
	private int date;

	@Column(name = "contact_id", nullable = false)
	private String contactId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "subject", nullable = false)
	private String subject;

	@Column(name = "message", nullable = false)
	private String message;

}
