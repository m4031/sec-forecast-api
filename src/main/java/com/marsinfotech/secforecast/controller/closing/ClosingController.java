package com.marsinfotech.secforecast.controller.closing;

import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.services.closing.ClosingServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = AppConstants.STOCKS_REQUEST_BASE_URL)
public class ClosingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClosingController.class);

	private String lastUpdateUser = "ABC";
	@Autowired
	private ClosingServiceI closingService;

	@GetMapping(AppConstants.GET_CLOSINGS)
	public ResponseEntity<Object> getClosings(@RequestParam int date) {
		LOGGER.info("Received getClosings request");
		try {
			List<Closing> closings = closingService.getClosings(date);
			if (closings != null) {
				return new ResponseEntity<Object>(closings, new HttpHeaders(), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Failed to retrieve closings", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			LOGGER.error("Error retrieving closings, error={0}", ex);
			return new ResponseEntity<Object>("Error while closings", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(AppConstants.GET_CLOSING)
	public ResponseEntity<Object> getClosing(@RequestParam int date, @RequestParam String ticker) {
		LOGGER.info("Received getClosing request");
		try {
			Closing closing = closingService.getClosing(date, ticker);
			if (closing != null) {
				return new ResponseEntity<Object>(closing, new HttpHeaders(), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Failed to retrieve closing", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			LOGGER.error("Error retrieving closing, error={0}", ex);
			return new ResponseEntity<Object>("Error while closing", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

	@PutMapping(path = AppConstants.UPDATE_CLOSING)
	public ResponseEntity<Object> updateOrInsertClosing(@RequestBody Closing closing){
		LOGGER.info("Received updateOrInsertClosing request closing={}", closing);
		try {
			boolean isUpdated = closingService.insertOrUpdate(closing, lastUpdateUser, ZonedDateTime.now());
			String message = isUpdated ? "Successfully updated" : "Failed to update";
			message += " closing";
			return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error updating closing closing={}, error={1}", closing, ex);
			return new ResponseEntity<Object>("Error updating closing", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping(path = AppConstants.DELETE_CLOSING)
	public ResponseEntity<Object> deleteClosing(@RequestParam int date, @RequestParam String ticker) {
		LOGGER.info("Received deleteClosing request date={}, ticker={}", date, ticker);
		try {
			boolean isDeleted = closingService.delete(date, ticker);
			String message = isDeleted ? "Successfully deleted" : "Failed to delete";
			message += " closing for date=" + date + " and ticker=" + ticker;
			return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.OK);
		}catch (Exception ex) {
			LOGGER.error("Error deleting closing for date={}, ticker={}, error={2}", date, ticker, ex);
			return new ResponseEntity<Object>("Error deleting closing for date={}, ticker={}", new HttpHeaders(),
					HttpStatus.FORBIDDEN);
		}
	}

}
