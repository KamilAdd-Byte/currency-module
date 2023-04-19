package com.rates.currency.scrapp;

import com.rates.currency.exception.ElementsCreateException;
import io.vavr.control.Try;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scraper {
    private final Document document;
    private String elementByClass;
    private String elementByTag;

    public Scraper(Document document, String byClass, String byTag) {
        this.document = document;
        this.elementByClass = byClass;
        this.elementByTag = byTag;
    }

    public Scraper(Document document) {
        this.document = document;
    }

    public Elements getElementsByClass() {
        return Try.of(() -> document.getElementsByClass(elementByClass))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByClass);
    }

    public Elements getElementsByTag() {
        return Try.of(() -> document.getElementsByTag(elementByTag))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByTag);
    }
}