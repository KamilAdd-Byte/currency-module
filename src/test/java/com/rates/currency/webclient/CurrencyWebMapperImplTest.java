package com.rates.currency.webclient;

import com.rates.currency.model.CurrencyDto;
import com.rates.currency.external.webclient.impl.CurrencyWebMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CurrencyWebMapperImplTest {

    CurrencyWebMapperImpl instance;

    @BeforeEach
    void getCurrencyWebClientInstance(){
        instance = CurrencyWebMapperImpl.getInstance();

    }

    @AfterEach
    void setReferenceCurrencyWebClientToNull(){
        instance = null;
    }

    @Test
    @DisplayName("should get currency to rest template by nbp api")
    void shouldGetCurrencyDtoToRestTemplate(){

        //given
        CurrencyDto eur = instance.getExchangeRatesOfLastTenDays("EUR");
        Class<CurrencyWebMapperImpl> currencyWebClientClass = CurrencyWebMapperImpl.class;
        Field[] declaredFields = currencyWebClientClass.getDeclaredFields();

        //then
        assertNotNull(eur);
        assertThat(eur.getCode()).isEqualTo("EUR");
        assertThat(declaredFields).hasSize(7);
    }

    @Test
    @DisplayName("get connection nbp api top last ten and response status 200")
    void getUrl_topLastTen_byNbpApi_USDCurrency_status200ok() {
        //given
        String urlUsd = "https://api.nbp.pl/api/exchangerates/rates/A/USD/last/10";

        when()
                .get(urlUsd)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("should get currency after set code to lower case")
    void shouldGetCurrencyAfterSetCodeToLowerCase(){
        //given
        CurrencyDto currencyLowerCase = instance.getExchangeRatesOfLastTenDays("eur");

        //then
        assertNotNull(currencyLowerCase);
        assertThat(currencyLowerCase.getCode()).isEqualTo("EUR");
        assertThat(currencyLowerCase.getCode()).isNotEqualTo("eur");
    }

    @Test
    @DisplayName("should catch exception for incorrect currency code")
    void shouldCatchExceptionForIncorrectCurrencyCode(){
        //given
        String inCorrectCurrencyCode = "us";

        //then
        assertThrows(Exception.class, () -> instance.getExchangeRatesOfLastTenDays(inCorrectCurrencyCode));
    }
}
