package com.rates.currency.scrapp.model.dto;

import com.rates.currency.model.basic.BasicCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@ToString
public class CurrencyExchangeDto extends BasicCurrency {
    private String country;
    private String number;

    public CurrencyExchangeDto(String code, String currency, String country, String number) {
        super(code, currency);
        this.country = country;
        this.number = number;
    }
}
