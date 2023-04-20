package com.rates.currency.scrapp.currency.service;

import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.model.websideline.WebSideLine;

import java.util.List;

public interface CurrencyCodesService {
    List<WebSideLine> getAllCodes();
    List<CurrencyExchangeDto> allCurrencies();
}
