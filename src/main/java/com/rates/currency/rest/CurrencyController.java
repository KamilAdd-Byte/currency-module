package com.rates.currency.rest;

import com.rates.currency.model.CurrencyDto;
import com.rates.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping(path = "/codes")
    public ResponseEntity<List<String>> getAllCodes () {
        List<String> allCodes = service.getAllCodes();
        return ResponseEntity.ok().body(allCodes);
    }

    @GetMapping(path = "/{code}/{table}/{date}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable("code") String code, @PathVariable("table") String table, @PathVariable("date")LocalDate date) {
        CurrencyDto currency = service.getCurrency(code, table, date);
        return ResponseEntity.ok().body(currency);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<CurrencyDto> getExchangeRatesOfLastTenDays (@PathVariable("code") String code) {
        CurrencyDto currency = service.getExchangeRatesOfLastTenDays(code);
        return ResponseEntity.ok().body(currency);
    }
}
