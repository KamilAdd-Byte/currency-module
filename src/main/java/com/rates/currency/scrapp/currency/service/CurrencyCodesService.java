package com.rates.currency.scrapp.currency.service;

import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.model.websideline.WebSideLine;

import java.util.List;
import java.util.Set;

public interface CurrencyCodesService {
    List<WebSideLine> getAllCodes();
    Set<CurrencyExchangeDto> allCurrencies();
}
