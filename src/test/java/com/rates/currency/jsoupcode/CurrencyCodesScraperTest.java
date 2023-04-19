package com.rates.currency.jsoupcode;

import com.rates.currency.scrapp.service.impl.CurrencyCodesServiceImpl;
import com.rates.currency.service.CurrencyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.when;

class CurrencyCodesScraperTest {

    @Autowired
    private CurrencyService service;

    @Test
    @DisplayName("get connection wikipedia and should status 200")
    void getConnectionWikipedia_andShouldStatus_200ok() {
        String url = CurrencyCodesServiceImpl.getUrlBasicCodeCurrency();

        when()
                .get(url)
                .then()
                .statusCode(200);
    }
}
