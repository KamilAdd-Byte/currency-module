package com.rates.currency.scrapp.cantor.service;

import com.rates.currency.scrapp.model.websideline.WebSideLine;

import java.util.List;

public interface RatesCantorService {
    List<WebSideLine> getAllRatesForCantor();
}
