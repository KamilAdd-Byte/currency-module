package com.rates.currency.scrapp.currency;

import com.rates.currency.message.LogMessage;
import com.rates.currency.scrapp.currency.scrapper.CurrencyCodesWebSideScrapper;
import com.rates.currency.scrapp.model.dto.CurrencyExchangeDto;
import com.rates.currency.scrapp.model.websideline.WebSideLine;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kamil.sulejewski
 * @apiNote Responsibility of the class is to prepare a list of currency exchanges dtos based on the webSideLines list received from WebSideScraper
 * @see CurrencyCodesWebSideScrapper
 */
@Slf4j
public class CurrencyExchangeObtainer {

    private final List<WebSideLine> webSideLines;

    @Getter
    private List<CurrencyExchangeDto> exchangeDtos;

    private CurrencyExchangeObtainer(List<WebSideLine> webSideLines) {
        this.webSideLines = webSideLines;
        prepareCurrenciesExchangeDtos();
    }

    public static CurrencyExchangeObtainer of(List<WebSideLine> webSideLines) {
        return new CurrencyExchangeObtainer(webSideLines);
    }

    private void prepareCurrenciesExchangeDtos() {
        exchangeDtos = webSideLines.stream()
                .map(webSideLine -> {
                    String[] webSideLineArray = splitWebSideLine(webSideLine.getLine());
                    return getCurrencyExchange(webSideLineArray);
                }).collect(Collectors.toList());
    }

    private CurrencyExchangeDto getCurrencyExchange (String[] webSideLineArray) {
        return Try.of(() -> obtainCurrency(webSideLineArray))
                .onFailure(throwable -> log.error(LogMessage.currencyExchangeFailureObtainMessage(throwable.getMessage())))
                .onSuccess(cxd -> log.info(LogMessage.currencyExchangeSuccessObtainMessage(cxd.getCurrency())))
                .getOrElse(new CurrencyExchangeDto());
    }

    private CurrencyExchangeDto obtainCurrency (String[] webSideLineArray) {
        return CurrencyExchangeDto.builder()
                .country(webSideLineArray[0])
                .currency(webSideLineArray[1])
                .code(webSideLineArray[2])
                .number(webSideLineArray[3])
                .build();
    }

    private String[] splitWebSideLine(String webSideLine) {
        String deleteWhitespace = StringUtils.deleteWhitespace(webSideLine);
        // FIXME: 20.04.2023 [] has 5 parts - we need more specification for split line
        if (Validator.validate(deleteWhitespace).isCorrect() || deleteWhitespace.contains("WIELONARODOWEGOPAŃSTW") || deleteWhitespace.contains("MIĘDZYNARODOWYFUNDUSZWALUTOW") || deleteWhitespace.contains("MIKRONEZJA")) {
            log.warn("We have a problem with obtain currency: {}", webSideLine);
            deleteWhitespace = Validator.defaultLineValue();
        }

        String[] splitByCharacterTypeCamelCase = StringUtils.splitByCharacterTypeCamelCase(deleteWhitespace);

        return splitByCharacterTypeCamelCase;
    }

}
class Validator {
    String value;
    @Getter
    boolean isCorrect;

    private Validator(String value) {
        this.value = value;
        this.isCorrect = validateLine();
    }

    private boolean validateLine() {
        // FIXME: 20.04.2023 Validacja code 3 reszta out; MIKRONEZJA, KOREA, MIĘDZYNARODOWYFUNDUSZWALUTOWY
        return value.contains("ANTARKTYDA Brak uniwersalnej waluty");
    }

    protected static String defaultLineValue () {
        return "POLSKA Zloty PLN 3220";
    }

    public static Validator validate (String value) {
        return new Validator(value);
    }

    private List<String> wrongWord () {
        ArrayList<String> wrongs = new ArrayList<>();
        wrongs.add("MIĘDZYNARODOWYFUNDUSZWALUTOWY");
        wrongs.add("MIKRONEZJA");
        wrongs.add("ANTARKTYDA Brak uniwersalnej waluty");
        wrongs.add("KOREA");
        wrongs.add("MIKRONEZJA");
        return wrongs;
    }
}


