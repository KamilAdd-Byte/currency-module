package com.rates.currency.scrapp.service.impl;

import com.rates.currency.scrapp.CurrencyCodesScraper;
import com.rates.currency.scrapp.DocumentCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyCodesServiceImpl {

    private static final String URL_BASIC_CODE_CURRENCY = "https://www.iban.pl/currency-codes";

    @Autowired
    private DocumentCreator documentCreator;

    public List<String> allCodesWithWikipedia() {
        return CurrencyCodesScraper.of(documentCreator.create(URL_BASIC_CODE_CURRENCY)).getCodes();
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }
}

