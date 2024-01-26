package com.marsinfotech.secforecast.services.closing;

import com.marsinfotech.secforecast.data.closing.Closing;
import java.time.ZonedDateTime;
import java.util.List;

public interface ClosingServiceI {

    List<Closing> getClosings(int date);

    Closing getClosing(int date, String ticker);

    boolean insertOrUpdate(Closing closing, String lastUpdateUser, ZonedDateTime lastUpdateTime);

    boolean delete(int date, String ticker);
}
