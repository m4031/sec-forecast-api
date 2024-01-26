package com.marsinfotech.secforecast.services.persistence;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.services.core.ServiceI;

import java.util.List;

public interface PersistenceReaderServiceI extends ServiceI {

    public List<Integer> getCalculationDates();

    public ForecastDate getForecastDate(int lastForecastDate);

    List<ForecastPrice> getForecastPrices(int date);

    List<ForecastPrice> getForecastPrices(int date, String algo);

    List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos);

    Contact getContact(String contactId);

    List<Contact> getContacts(String email);

    List<Contact> getContacts(String email, int date);

    List<Closing> getClosings(int date);

    Closing getClosing(int date, String ticker);

    NewsSubscription getSubscription(String email);
    List<NewsSubscription> getSubscriptions(String name);
    NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId);

}
