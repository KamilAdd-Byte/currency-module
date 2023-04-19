package com.rates.currency.scrapp;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CurrencyCodesScraper extends Scraper {

    private static final String SELECT_QUERY = "tr";
    private static final String ELEMENT_BY_CLASS = "table table-bordered downloads tablesorter";
    private static final String ELEMENT_BY_TAG = "p";

    @Getter
    private List<String> codes;

    private CurrencyCodesScraper(Document document) {
        super(document, ELEMENT_BY_CLASS, ELEMENT_BY_TAG);
        scrapAllCodesList();
    }

    public static CurrencyCodesScraper of(Document document) {
        return new CurrencyCodesScraper(document);
    }

    private void scrapAllCodesList() {
        if (codes == null) {
            codes = new ArrayList<>();
        }

        Elements elementsByClass = getElementsByClass();

        for (Element element : elementsByClass.select(SELECT_QUERY)) {
               codes.add(element.text());
           }
        }
    }
