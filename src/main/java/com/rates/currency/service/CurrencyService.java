package com.rates.currency.service;

import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.api.model.dto.CurrencyExchangeDto;
import com.rates.currency.api.model.websideline.WebSideLine;
import java.util.List;
import java.util.Set;

public interface CurrencyService {
    List<WebSideLine> getAllCodes();
    List<WebSideLine> getAllRatesForCantor();
    Set<CurrencyExchangeDto> currencyExchangeDtos();

    CurrencyDto getCurrency(String code, String table, String date);
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
}
