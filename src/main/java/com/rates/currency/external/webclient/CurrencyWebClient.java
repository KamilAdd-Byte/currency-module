package com.rates.currency.external.webclient;

import com.rates.currency.model.CurrencyDto;
import java.time.LocalDate;

public interface CurrencyWebClient {
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
    CurrencyDto getOneCurrencyBy(String code, String table, LocalDate date);
}
