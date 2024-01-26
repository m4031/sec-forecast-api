package com.marsinfotech.secforecast.data.closing;
import com.marsinfotech.secforecast.persistence.db.entity.DatabaseEntity;
import lombok.Data;
@Data
public class Closing extends DatabaseEntity {

	private int date;
	private String ticker;
	private String country;
	private String currency;
	private double openPrice;
	private double highestPrice;
	private double lowestPrice;
	private double closePrice;
	private double adjustedClosePrice;
	private double volume;

}
