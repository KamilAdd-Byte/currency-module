package com.rates.currency.service;

import com.rates.currency.model.CurrencyDto;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public interface CurrencyService {
    List<String> getAllCodes();
    CurrencyDto getCurrency(String code, String table, LocalDate date);
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
}
