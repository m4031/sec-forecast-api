package com.marsinfotech.secforecast.persistence.db.converter;

public interface DataConverter<I, D> {

    D convert(I inputObject);
}
