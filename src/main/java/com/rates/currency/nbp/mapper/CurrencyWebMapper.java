package com.rates.currency.nbp.mapper;

import com.rates.currency.nbp.dto.CurrencyDto;

public interface CurrencyWebMapper {
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
    CurrencyDto getOneCurrencyBy(String code, String table, String date);
}
