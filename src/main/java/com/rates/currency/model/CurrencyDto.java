package com.rates.currency.model;

import lombok.*;
import java.util.List;

@Getter
@Builder
@ToString
public class CurrencyDto {
    private String table;
    private String currency;
    private String code;
    private List<RatesDto> rates;
}
