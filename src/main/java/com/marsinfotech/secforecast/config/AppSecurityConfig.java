package com.marsinfotech.secforecast.config;

import com.marsinfotech.secforecast.data.AppConstants;
import com.marsinfotech.secforecast.exception.handler.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

import static com.marsinfotech.secforecast.data.AppConstants.*;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
//@EnableWebSecurity
public class AppSecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSecurityConfig.class);

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;
    @Value("${cors.allowedMethods}")
    private String allowedMethods;
    @Value("${cors.allowedHeaders}")
    private String allowedHeaders;

    @Value("${cors.maxAge}")
    private long corsMaxAge;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

//	@Autowired
//	private GlobalExceptionHandler globalExceptionHandler;

    //	@Autowired
//	private CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        try {
            JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

            /** CSRF (cross site request forgery) Protection enabled */
            http.csrf(csrf -> csrf.disable());
//            CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//            XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
//            // set the name of the attribute the CsrfToken will be populated on
//            delegate.setCsrfRequestAttributeName("_csrf");
//            // Use only the handle() method of XorCsrfTokenRequestAttributeHandler and the
//            // default implementation of resolveCsrfTokenValue() from CsrfTokenRequestHandler
//            CsrfTokenRequestHandler requestHandler = delegate::handle;
//            http.csrf(csrf -> csrf
//                    .csrfTokenRepository(tokenRepository)
//                    .csrfTokenRequestHandler(requestHandler)
//                    .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
//            );

            /** CORS Protection (Cross origin resource sharing - only occurs if request is coming from a browser client
             *  having different Url than server. Url diff include Protocol, hostname, port)
             * */
            http.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(convertToList(allowedOrigins));
                    config.setAllowedMethods(convertToList(allowedMethods));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(convertToList(allowedHeaders));
//						config.setMaxAge(3600L);
                    config.setMaxAge(corsMaxAge);
                    return config;
                }
            }));

            /** JWT Security to non-public/private Urls */
            http.authorizeHttpRequests(auth -> auth
                    .requestMatchers(antMatcher("/docs/**"), antMatcher("/swagger-ui/**"),
                            antMatcher("/swagger-resources/**"),
                            antMatcher("/docs/swagger-config/**"),
                            antMatcher("/h2-console/**"),
//                            antMatcher("/h2-console/login.do"),
                            antMatcher("/favicon.ico/**"),
                            antMatcher("/webjars/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + HOME),
                            antMatcher(STOCKS_REQUEST_BASE_URL + CONTACT_US),
                            antMatcher(STOCKS_REQUEST_BASE_URL + NEWS_SUBSCRIPTION),
                            antMatcher(USERS_BASE_URL + AUTHENTICATE_CLIENT_APP)).permitAll()
                    .requestMatchers(antMatcher(STOCKS_REQUEST_BASE_URL + UPDATE_FORECAST_PRICES + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + DELETE_FORECAST_PRICES + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + UPDATE_FORECAST_DATE + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + DELETE_FORECAST_DATE + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + UPDATE_CLOSING + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + DELETE_CLOSING + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + GET_CLOSING + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + GET_CLOSINGS + "/**")).hasRole(ADMIN_ROLE)
                    .requestMatchers(antMatcher(STOCKS_REQUEST_BASE_URL + AppConstants.GET_FORECAST_PRICES + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + GET_FORECAST_PRICES + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + GET_FORECAST_DATE + "/**"),
                            antMatcher(STOCKS_REQUEST_BASE_URL + GET_CALCULATION_DATES + "/**")
                    ).hasAnyRole(READY_ONLY_ROLE, READ_WRITE_ROLE, ADMIN_ROLE)
                    .anyRequest().authenticated()
            );

            /**
             * Securing application using openid-connect protocol using Keycloak (an opensource Identity and
             * Access management system by ReadHat) by configuring app as oauth2ResourceServer
             */
            http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)).
                    authenticationEntryPoint(authenticationEntryPoint));
            //				accessDeniedHandler((AccessDeniedHandler) accessDeniedHandler);

            /**
             * Session Creation Policy
             * https://github.com/spring-projects/spring-security/issues/12310
             */
            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            /**
             * This configuration is needed to view the /h2-console with out any issues.
             * Since H2 Console uses frames to display the UI, we need to allow the frames.
             * Otherwise since by default Spring Security consider X-Frame-Options: DENY
             * to avoid Clickjacking attacks, the /h2-console will not work properly.
             * More details can be found at
             * https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/headers.html#headers-frame-options
             */
            http.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
            return http.build();
        } catch (Exception ex) {
            LOGGER.error("Error configuring App security (Cors, Csrf, Oauth2, secured and public endpoints, ...etc), error={0}", ex);
        }
        return null;
    }

    private List<String> convertToList(String values) {
        List<String> valuesList = new ArrayList<>();
        for (String str : values.split(",")) {
            valuesList.add(str.trim());
        }
        return valuesList;
    }
}
