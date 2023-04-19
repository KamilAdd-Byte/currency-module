package com.rates.currency.jsoupcode;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractJsoupProcessor {
    private static final String URL_BASIC_CODE_CURRENCY = "https://www.iban.pl/currency-codes";
    private static List<String> codes;

    public static String getUrlBasicCodeCurrency(){
        return URL_BASIC_CODE_CURRENCY;
    }

    public static List<String> allCodesIso4217() {
        if (codes==null){
            codes = new ArrayList<>();
        }
        try {
            Document document = Jsoup.connect(URL_BASIC_CODE_CURRENCY).get();
            Elements elements_codes = document.getElementsByClass("table table-bordered downloads tablesorter");
            for (Element elements_code : elements_codes.select("tr")) {
                codes.add(elements_code.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       return codes;
    }
}
