package com.marsinfotech.secforecast.data;

public class AppConstants {
    public static final String STOCKS_REQUEST_BASE_URL = "/secforecast/stocks/v0";
    public static final String BONDS_REQUEST_BASE_URL = "/secforecast/bonds/v0";

    public static final String USERS_BASE_URL = "/secforecast/users/v0";

    /** Authenticated & only Admin role Endpoints */
    public static final String UPDATE_FORECAST_DATE = "/updateForecastDate";
    public static final String DELETE_FORECAST_DATE = "/deleteForecastDate";
    public static final String UPDATE_FORECAST_PRICES = "/updateForecastPrices";
    public static final String DELETE_FORECAST_PRICES = "/deleteForecastPrices";
    public static final String UPDATE_CLOSING = "/updateClosing";
    public static final String DELETE_CLOSING = "/deleteClosing";


    /** Authenticated (user or admin role) Endpoints */
    public static final String GET_CALCULATION_DATES = "/getCalculationDates";
    public static final String GET_FORECAST_DATE = "/getForecastDate";
    public static final String GET_FORECAST_PRICES = "/getForecastPrices";

    public static final String GET_CLOSINGS = "/getClosings";
    public static final String GET_CLOSING = "/getClosing";


    /** Public Rest Endpoints */
    public static final String HOME = "/home";
    public static final String CONTACT_US = "/contact";
    public static final String NEWS_SUBSCRIPTION = "/newssignup";

    public static final String AUTHENTICATE_CLIENT_APP = "/authenticateClientApp";

    /** ROLE / AUTHORITIES */
    public static final String READY_ONLY_ROLE = "SF_READY_ONLY_ROLE"; // R -> "USER"
    public static final String READ_WRITE_ROLE = "SF_READ_AND_WRITE_ROLE"; //R+W
    public static final String ADMIN_ROLE = "SF_ADMIN_ROLE"; //ADMIN R+W+D

}
