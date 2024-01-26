package com.marsinfotech.secforecast.services.persistence;

import com.marsinfotech.secforecast.data.closing.Closing;
import com.marsinfotech.secforecast.data.contact.Contact;
import com.marsinfotech.secforecast.data.forecast.ForecastDate;
import com.marsinfotech.secforecast.data.forecast.ForecastPrice;
import com.marsinfotech.secforecast.data.news.NewsSubscription;
import com.marsinfotech.secforecast.services.core.ServiceI;
import java.time.ZonedDateTime;
import java.util.List;

public interface PersistenceWriterServiceI extends ServiceI {

    boolean updateOrInsertForecastDate(ForecastDate forecastDate, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean deleteForecastDate(int date);

    List<ForecastPrice> updateOrInsertForecastPrices(List<ForecastPrice> forecastPrices, String lastUpdateUser,
                                                            ZonedDateTime lastUpdateTime, boolean isUpdateOneByOne);
    boolean deleteForecastPrices(int date);

    boolean deleteForecastPrices(int date, String algo);

    boolean deleteForecastPrices(int date, List<String> tickers, List<String> algos);

    Contact updateContact(Contact contact, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean deleteContact(String contactId);

    boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean delete(int date, String ticker);

    NewsSubscription insertOrUpdate(NewsSubscription subscription, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean delete(String email);

}
