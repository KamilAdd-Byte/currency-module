package com.rates.currency.service;

import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.model.websideline.WebSideLine;
import java.util.List;

public interface CurrencyService {
    List<WebSideLine> getAllCodes();
    List<WebSideLine> getAllRatesForCantor();
    List<CurrencyExchangeDto> currencyExchangeDtos();

    CurrencyDto getCurrency(String code, String table, String date);
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
}
