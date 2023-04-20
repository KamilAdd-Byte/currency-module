package com.rates.currency.nbp.dto;

import com.rates.currency.model.basic.BasicCurrency;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SuperBuilder
public class CurrencyDto extends BasicCurrency {
    private String table;
    private List<RatesDto> rates;

    public CurrencyDto(String code, String currency, String table, List<RatesDto> rates) {
        super(code, currency);
        this.table = table;
        this.rates = rates;
    }
}