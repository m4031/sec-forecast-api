package com.marsinfotech.secforecast.services.persistence;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.services.core.AbstractService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Service
public class PersistenceService extends AbstractService implements  PersistenceServiceI {

    private final static String SERVICE_NAME = "PersistenceService";

    @Autowired
    private PersistenceReaderServiceI reader;
    @Autowired
    private PersistenceWriterServiceI writer;

    public PersistenceService() {
        super(SERVICE_NAME);
    }

    @Override
    public List<Integer> getCalculationDates() {
        return reader.getCalculationDates();
    }

    @Override
    public ForecastDate getForecastDate(int lastForecastDate) {
        return reader.getForecastDate(lastForecastDate);
    }

    @Override
    public boolean updateOrInsertForecastDate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return writer.updateOrInsertForecastDate(forecastDate, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteForecastDate(int date) {
        return writer.deleteForecastDate(date);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date) {
        return reader.getForecastPrices(date);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, String algo) {
        return reader.getForecastPrices(date, algo);
    }

    @Override
    public List<ForecastPrice> getForecastPrices(int date, List<String> tickers, List<String> algos) {
        return reader.getForecastPrices(date, tickers, algos);
    }

    @Override
    public List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                                           ZonedDateTime lastUpdateTime, boolean isUpdateOneByOne) {
        return writer.updateOrInsertForecastPrices(forecastPrices, lastUpdateUser, lastUpdateTime, isUpdateOneByOne);
    }

    @Override
    public boolean deleteForecastPrices(int date) {
        return writer.deleteForecastPrices(date);
    }

    @Override
    public boolean deleteForecastPrices(int date, String algo) {
        return writer.deleteForecastPrices(date, algo);
    }

    @Override
    public boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos) {
        return writer.deleteForecastPrices(date, tickers, algos);
    }

    @Override
    public Contact getContact(String contactId) {
        return reader.getContact(contactId);
    }

    @Override
    public List<Contact> getContacts(String email) {
        return reader.getContacts(email);
    }

    @Override
    public List<Contact> getContacts(String email, int date) {
        return reader.getContacts(email, date);
    }

    @Override
    public Contact updateContact(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return writer.updateContact(contact, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteContact(String contactId) {
        return writer.deleteContact(contactId);
    }

    @Override
    public List<Closing> getClosings(int date) {
        return reader.getClosings(date);
    }

    @Override
    public Closing getClosing(int date, String ticker) {
        return reader.getClosing(date, ticker);
    }

    @Override
    public boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return writer.insertOrUpdate(closing, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(int date, String ticker) {
        return writer.delete(date, ticker);
    }

    @Override
    public NewsSubscription getSubscription(String email) {
        return reader.getSubscription(email);
    }

    @Override
    public List<NewsSubscription> getSubscriptions(String name) {
        return reader.getSubscriptions(name);
    }

    @Override
    public NewsSubscription getSubscriptionBySubscriptionId(String subscriptionId) {
        return reader.getSubscriptionBySubscriptionId(subscriptionId);
    }

    @Override
    public NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return writer.insertOrUpdate(subscription, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(String email) {
        return writer.delete(email);
    }

}
