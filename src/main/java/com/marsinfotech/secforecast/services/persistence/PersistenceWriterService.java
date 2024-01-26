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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersistenceWriterService extends AbstractService implements PersistenceWriterServiceI {

    private final static String SERVICE_NAME = "PersistenceWriterService";
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceWriterService.class);

    @Autowired
    private ForecastDateRepositoryAdapter forecastDateRepoAdapter;

    @Autowired
    private ForecastPriceRepositoryAdapter forecastPriceRepoAdapter;

    @Autowired
    private ContactRepositoryAdapter contactRepositoryAdapter;

    @Autowired
    private NewsSubscriptionRepositoryAdapter newsSubscriptionRepositoryAdapter;

    @Autowired
    private ClosingRepositoryAdapter closingRepositoryAdapter;

    public PersistenceWriterService() {
        super(SERVICE_NAME);
    }

    @Override
    public boolean updateOrInsertForecastDate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return forecastDateRepoAdapter.insertOrUpdate(forecastDate, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteForecastDate(int date) {
        return forecastDateRepoAdapter.delete(date);
    }

    @Override
    public List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                                            ZonedDateTime lastUpdateTime, boolean isUpdateOneByOne) {
        return forecastPriceRepoAdapter.updateOrInsertForecastPrices(forecastPrices, lastUpdateUser, lastUpdateTime, isUpdateOneByOne);
    }

    @Override
    public boolean deleteForecastPrices(int date) {
        return forecastPriceRepoAdapter.deleteForecastPrices(date, null, null);
    }

    @Override
    public boolean deleteForecastPrices(int date, String algo) {
        List<String> algos = new ArrayList<>();
        algos.add(algo);
        return forecastPriceRepoAdapter.deleteForecastPrices(date, null, algos);
    }

    @Override
    public boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos) {
        return forecastPriceRepoAdapter.deleteForecastPrices(date, tickers, algos);
    }


    @Override
    public Contact updateContact(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return contactRepositoryAdapter.insertOrUpdate(contact, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean deleteContact(String contactId) {
        return contactRepositoryAdapter.delete(contactId);
    }

    @Override
    public boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return closingRepositoryAdapter.insertOrUpdate(closing, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(int date, String ticker) {
        return closingRepositoryAdapter.delete(date, ticker);
    }

    @Override
    public NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime) {
        return newsSubscriptionRepositoryAdapter.insertOrUpdate(subscription, lastUpdateUser, lastUpdateTime);
    }

    @Override
    public boolean delete(String email) {
        return newsSubscriptionRepositoryAdapter.delete(email);
    }

}
