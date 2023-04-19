package com.rates.currency.external.model;

import com.rates.currency.model.RatesDto;
import lombok.Getter;
import java.util.List;

@Getter
public class CurrencyNbpExternalModel {
    private String table;
    private String currency;
    private String code;
    private List<RatesDto> rates;
}
