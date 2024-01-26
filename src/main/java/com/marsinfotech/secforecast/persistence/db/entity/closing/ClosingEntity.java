package com.marsinfotech.secforecast.persistence.db.entity.closing;
import com.marsinfotech.secforecast.persistence.db.entity.DatabaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "closing_info")
@Data
public class ClosingEntity extends DatabaseEntity {

	@Column(name = "date", nullable = false)
	private int date;

	@Column(name = "ticker", nullable = false)
	private String ticker;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "currency", nullable = false)
	private String currency;

	@Column(name = "price_open", nullable = false)
	private double openPrice;

	@Column(name = "price_high", nullable = false)
	private double highestPrice;

	@Column(name = "price_low", nullable = false)
	private double lowestPrice;

	@Column(name = "price_close", nullable = false)
	private double closePrice;

	@Column(name = "volume_high", nullable = false)
	private double highestVolume;

	@Column(name = "volume_low", nullable = false)
	private double lowestVolume;
}
