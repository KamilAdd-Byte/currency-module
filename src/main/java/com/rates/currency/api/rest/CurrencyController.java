package com.rates.currency.api.rest;

import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.model.websideline.WebSideLine;
import com.rates.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping(path = "/codes")
    public ResponseEntity<List<WebSideLine>> getAllCodes () {
        List<WebSideLine> allCodes = service.getAllCodes();
        return ResponseEntity.ok().body(allCodes);
    }

    @GetMapping(path = "/currencies")
    public ResponseEntity<Set<CurrencyExchangeDto>> getAllCurrencies () {
        Set<CurrencyExchangeDto> currencyExchangeDtos = service.currencyExchangeDtos();
        return ResponseEntity.ok().body(currencyExchangeDtos);
    }


    @GetMapping(path = "/{code}/{table}/{date}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable("code") String code, @PathVariable("table") String table, @PathVariable("date") String date) {
        String id = UUID.randomUUID().toString();
        Date requestDate = new Date();
        CurrencyDto currency = service.getCurrency(code, table, date);
       return ResponseEntity.ok().body(currency);
        //return new ResponseEntity<>(new CurrencyResponse(id, requestDate, date, currency), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<CurrencyDto> getExchangeRatesOfLastTenDays (@PathVariable("code") String code) {
        CurrencyDto currency = service.getExchangeRatesOfLastTenDays(code);
        return ResponseEntity.ok().body(currency);
    }

    @GetMapping(path = "/cantor")
    public ResponseEntity<List<WebSideLine>> getRatesExampleCantor () {
        List<WebSideLine> allRatesForCantor = service.getAllRatesForCantor();
        return ResponseEntity.ok().body(allRatesForCantor);
    }
}
