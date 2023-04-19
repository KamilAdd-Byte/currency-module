package com.rates.currency.model;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class RatesDto {
    private String no;
    private String effectiveDate;
    private double mid;

    public RatesDto(String no, String effectiveDate, double mid) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.mid = mid;
    }
}
