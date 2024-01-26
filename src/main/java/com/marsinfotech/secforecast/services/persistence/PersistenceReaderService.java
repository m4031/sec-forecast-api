package com.marsinfotech.secforecast.services.persistence;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.persistence.db.adapter.closing.ClosingRepositoryAdapter;
import com.marsinfotech.secforecast.persistence.db.adapter.contact.ContactRepositoryAdapter;
import com.marsinfotech.secforecast.persistence.db.adapter.forecast.ForecastDateRepositoryAdapter;
import com.marsinfotech.secforecast.persistence.db.adapter.forecast.ForecastPriceRepositoryAdapter;
import com.marsinfotech.secforecast.persistence.db.adapter.news.NewsSubscriptionRepositoryAdapter;
import com.marsinfotech.secforecast.services.core.AbstractService;
import com.marsinfotech.secforecast.services.forecast.ForecastDateService;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true, noRollbackFor = {HibernateException.class})
@Service
public class PersistenceReaderService extends AbstractService implements PersistenceReaderServiceI {

    private final static String SERVICE_NAME = "PersistenceReaderService";
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastDateService.class);

    @Autowired
    private ForecastDateRepositoryAdapter forecastDateRepoAdapter;

    @Autowired
    private ForecastPriceRepositoryAdapter forecastPriceRepoAdapter;

    @Autowired
    private ContactRepositoryAdapter contactRepositoryAdapter;

    @Autowired
    private ClosingRepositoryAdapter closingRepositoryAdapter;

    @Autowired
    private NewsSubscriptionRepositoryAdapter newsSubscriptionRepositoryAdapter;

    public PersistenceReaderService() {
        super(SERVICE_NAME);
    }

    @Override
    public List<Integer> getCalculationDates() {
        return forecastDateRepoAdapter.getCalculationDates();
    }

    @Override
    public ForecastDate getForecastDate(int lastForecastDate) {
        return forecastDateRepoAdapter.getForecastDate(lastForecastDate);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date) {
        return forecastPriceRepoAdapter.getForecastPrices(date, null, null);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, String algo) {
        List<String> algos = new ArrayList<String>();
        algos.add(algo);
        return forecastPriceRepoAdapter.getForecastPrices(date, null, algos);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos) {
        return forecastPriceRepoAdapter.getForecastPrices(date, tickers, algos);
    }

    @Override
    public Contact getContact(String contactId) {
        return contactRepositoryAdapter.getContactMessage(contactId);
    }

    @Override
    public List<Contact> getContacts(String email) {
        return contactRepositoryAdapter.getContactMessages(email);
    }

    @Override
    public List<Contact> getContacts(String email, int date) {
        return contactRepositoryAdapter.getContactMessages(email, date);
    }

    @Override
    public List<Closing> getClosings(int date) {
        return closingRepositoryAdapter.getClosings(date);
    }

    @Override
    public Closing getClosing(int date, String ticker) {
        return closingRepositoryAdapter.getClosing(date, ticker);
    }

    @Override
    public NewsSubscription getSubscription(String email) {
        return newsSubscriptionRepositoryAdapter.getSubscription(email);
    }

    @Override
    public List<NewsSubscription> getSubscriptions(String name) {
        return newsSubscriptionRepositoryAdapter.getSubscriptions(name);
    }

    @Override
    public NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId) {
        return newsSubscriptionRepositoryAdapter.getSubscriptionBySubscriptionId(subscriptionId);
    }
}
