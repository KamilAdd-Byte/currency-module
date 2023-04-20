package com.rates.currency.scrapp.cantor.service.impl;

import com.rates.currency.scrapp.utils.DocumentCreator;
import com.rates.currency.scrapp.cantor.scrapper.RatesCantorScrapper;
import com.rates.currency.scrapp.model.websideline.WebSideLine;
import com.rates.currency.scrapp.cantor.service.RatesCantorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatesCantorServiceImpl implements RatesCantorService {
    private static final String URL_BASIC_RATES = "http://www.kantor-uno.pl/";

    @Autowired
    private DocumentCreator documentCreator;

    @Override
    public List<WebSideLine> getAllRatesForCantor() {
        return RatesCantorScrapper.of(documentCreator.create(URL_BASIC_RATES)).getWebSideLines();
    }
}
