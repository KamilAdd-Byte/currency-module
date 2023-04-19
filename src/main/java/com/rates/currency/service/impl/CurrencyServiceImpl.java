package com.rates.currency.service.impl;

import com.rates.currency.model.CurrencyDto;
import com.rates.currency.jsoupcode.service.impl.AbstractJsoupProcessorServiceImpl;
import com.rates.currency.service.CurrencyService;
import com.rates.currency.external.webclient.CurrencyWebClient;
import com.rates.currency.external.webclient.impl.CurrencyWebClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyWebClient currencyWebClient;
    @Autowired
    private AbstractJsoupProcessorServiceImpl abstractJsoupProcessorService;

    @Autowired
    public CurrencyServiceImpl(CurrencyWebClientBuilder currencyWebClient) {
        this.currencyWebClient = currencyWebClient;
    }

    /**
     * @return 
     */
    @Override
    public List<String> getAllCodes() {
        return abstractJsoupProcessorService.allCodesWithWikipedia();
    }

    /**
     * @param code currency is a three-letter String and standard ISO 4217
     * @param table currency is three-options 'A' 'B' or 'C'
     * @param date of publication of the value for the currency
     * @return new CurrencyDto if exist
     */
    @Override
    public CurrencyDto getCurrency(String code, String table, LocalDate date) {
        return this.currencyWebClient.getOneCurrencyBy(code,table,date);
    }

    /**
     * @param code it's currency code standard ISO 4217
     * @return currency and ten rates get to nbp api by rest template
     */
    public CurrencyDto getExchangeRatesOfLastTenDays(String code) {
        return this.currencyWebClient.getExchangeRatesOfLastTenDays(code);
    }
}
