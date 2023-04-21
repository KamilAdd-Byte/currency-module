package com.rates.currency.api.rest;

import com.rates.currency.api.response.CurrencyCodeResponse;
import com.rates.currency.api.response.CurrencyResponse;
import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.api.model.dto.CurrencyExchangeDto;
import com.rates.currency.api.model.websideline.WebSideLine;
import com.rates.currency.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1")
@Api(tags = {"Currency API"}, description = "Services for currencies. Get currency from nbp.api or data from scrap")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping(path = "/codes")
    @ApiOperation("Get all codes")
    public ResponseEntity<List<WebSideLine>> getAllCodes () {
        List<WebSideLine> allCodes = service.getAllCodes();
        return ResponseEntity.ok().body(allCodes);
    }

    @GetMapping(path = "/currencies")
    @ApiOperation("Get all currencies")
    public ResponseEntity<CurrencyCodeResponse> getAllCurrencies () {
        String id = UUID.randomUUID().toString();
        Date requestDate = new Date();
        Set<CurrencyExchangeDto> currencyExchangeDtos = service.currencyExchangeDtos();
        return new ResponseEntity<>(new CurrencyCodeResponse(id, requestDate, currencyExchangeDtos), HttpStatus.OK);
    }


    @GetMapping(path = "/{code}/{table}/{date}")
    @ApiOperation("Get rates for currency by code, table and date")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable("code") String code, @PathVariable("table") String table, @PathVariable("date") String date) {
        String id = UUID.randomUUID().toString();
        Date requestDate = new Date();
        CurrencyDto currency = service.getCurrency(code, table, date);
       return new ResponseEntity<>(new CurrencyResponse(id, requestDate, date, currency), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}")
    @ApiOperation("Get exchange rates of ten days for currency by CODE")
    @ApiModelProperty
    public ResponseEntity<CurrencyDto> getExchangeRatesOfLastTenDays (@PathVariable("code") String code) {
        CurrencyDto currency = service.getExchangeRatesOfLastTenDays(code);
        return ResponseEntity.ok().body(currency);
    }

    @GetMapping(path = "/cantor")
    @ApiOperation("Get rates currency exchange from cantor")
    public ResponseEntity<List<WebSideLine>> getRatesExampleCantor () {
        List<WebSideLine> allRatesForCantor = service.getAllRatesForCantor();
        return ResponseEntity.ok().body(allRatesForCantor);
    }
}
