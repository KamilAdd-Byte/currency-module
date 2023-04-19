package com.rates.currency.service;

import com.rates.currency.model.CurrencyDto;
import java.util.List;

public interface CurrencyService {
    List<String> getAllCodes();
    CurrencyDto getCurrency(String code, String table, String date);
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
}
