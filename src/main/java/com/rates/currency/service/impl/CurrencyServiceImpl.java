package com.rates.currency.service.impl;

import com.rates.currency.model.CurrencyDto;
import com.rates.currency.scrapp.service.impl.CurrencyCodesServiceImpl;
import com.rates.currency.service.CurrencyService;
import com.rates.currency.external.webclient.impl.CurrencyWebMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final com.rates.currency.external.webclient.CurrencyWebMapper currencyWebMapper;
    @Autowired
    private CurrencyCodesServiceImpl abstractJsoupProcessorService;

    @Autowired
    public CurrencyServiceImpl(CurrencyWebMapperImpl currencyWebClient) {
        this.currencyWebMapper = currencyWebClient;
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
    public CurrencyDto getCurrency(String code, String table, String date) {
        return this.currencyWebMapper.getOneCurrencyBy(code,table,date);
    }

    /**
     * @param code it's currency code standard ISO 4217
     * @return currency and ten rates get to nbp api by rest template
     */
    public CurrencyDto getExchangeRatesOfLastTenDays(String code) {
        return this.currencyWebMapper.getExchangeRatesOfLastTenDays(code);
    }
}
