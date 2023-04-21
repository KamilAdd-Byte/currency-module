package com.rates.currency.service.impl;

import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.scrapp.currency.service.CurrencyCodesService;
import com.rates.currency.api.model.dto.CurrencyExchangeDto;
import com.rates.currency.api.model.websideline.WebSideLine;
import com.rates.currency.scrapp.cantor.service.impl.RatesCantorServiceImpl;
import com.rates.currency.service.CurrencyService;
import com.rates.currency.nbp.mapper.impl.CurrencyWebMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final com.rates.currency.nbp.mapper.CurrencyWebMapper currencyWebMapper;
    @Autowired
    private CurrencyCodesService codesService;

    @Autowired
    private RatesCantorServiceImpl ratesCantorService;

    @Autowired
    public CurrencyServiceImpl(CurrencyWebMapperImpl currencyWebClient) {
        this.currencyWebMapper = currencyWebClient;
    }

    /**
     * @return 
     */
    @Override
    public List<WebSideLine> getAllCodes() {
        return codesService.getAllCodes();
    }

    @Override
    public List<WebSideLine> getAllRatesForCantor() {
        return ratesCantorService.getAllRatesForCantor();
    }

    @Override
    public Set<CurrencyExchangeDto> currencyExchangeDtos() {
        return codesService.allCurrencies();
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
