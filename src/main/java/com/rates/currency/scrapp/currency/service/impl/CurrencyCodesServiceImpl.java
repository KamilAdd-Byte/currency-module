package com.rates.currency.scrapp.currency.service.impl;

import com.rates.currency.scrapp.currency.CurrencyExchangeObtainer;
import com.rates.currency.scrapp.currency.scrapper.CurrencyCodesWebSideScrapper;
import com.rates.currency.scrapp.currency.service.CurrencyCodesService;
import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.utils.DocumentCreator;
import com.rates.currency.scrapp.model.websideline.WebSideLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class CurrencyCodesServiceImpl implements CurrencyCodesService {

    private static final String URL_BASIC_CODE_CURRENCY = "https://www.iban.pl/currency-codes";

    @Autowired
    private DocumentCreator documentCreator;

    @Override
    public List<WebSideLine> getAllCodes() {
        return CurrencyCodesWebSideScrapper.of(documentCreator.create(URL_BASIC_CODE_CURRENCY)).getWebSideLines();
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }


    @Override
    public Set<CurrencyExchangeDto> allCurrencies() {
        List<WebSideLine> webSideLines = getAllCodes();
        return CurrencyExchangeObtainer.of(webSideLines).getExchangeDtos();
    }
}

