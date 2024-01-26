package com.marsinfotech.secforecast.controller.user;

import com.marsinfotech.secforecast.controller.forecast.ForecastDateController;
import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.data.user.AuthResponse;
import com.marsinfotech.secforecast.data.user.AuthRequest;
import com.marsinfotech.secforecast.services.news.NewsSubscriptionServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = AppConstants.USERS_BASE_URL)
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateController.class);

	private String lastUpdateUser = "ABC";

	@Value("${non.browser.client.app.auth.url}")
	private String clientAppAuthUrl;

	@Autowired
	private NewsSubscriptionServiceI newsSubscriptionService;

	@PostMapping(path=AppConstants.AUTHENTICATE_CLIENT_APP)
	public ResponseEntity<Object> authenticateClientApp(@RequestBody AuthRequest appAuthRequest) {
		LOGGER.info("Received authenticateClientApp request");
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
			headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data
			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
			requestBody.add("grant_type", appAuthRequest.getGrantType());
			requestBody.add("client_id", appAuthRequest.getClientId());
			requestBody.add("client_secret", appAuthRequest.getClientSecret());
			HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
			ResponseEntity<AuthResponse> response = restTemplate.exchange(clientAppAuthUrl, HttpMethod.POST, formEntity, AuthResponse.class);

//			AuthResponse clientAppAuthResponse = restTemplate.postForObject(clientAppAuthUrl, appAuthRequest, AuthResponse.class);
			if (response == null) {
				return new ResponseEntity<Object>("Failed to get access authenticate", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<Object>(response.getBody(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}catch (Exception ex) {
			LOGGER.error("Error client App authentication , error={0}", ex);
			return new ResponseEntity<Object>("Error while doing client App authentication", new HttpHeaders(),
					HttpStatus.UNAUTHORIZED);
		}
	}

}
