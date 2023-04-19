package com.rates.currency.external.webclient;

import com.rates.currency.model.CurrencyDto;

public interface CurrencyWebMapper {
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
    CurrencyDto getOneCurrencyBy(String code, String table, String date);
}
