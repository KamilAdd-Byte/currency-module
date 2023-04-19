package com.rates.currency.message;

public class LogMessage {

    private static final String JSOUP_CREATED_DOCUMENT = "Document is created";

    private LogMessage() {
        throw new IllegalStateException("Utility class");
    }

    // Jsoup Document
    public static String documentCreatedMessage() {
        return JSOUP_CREATED_DOCUMENT;
    }
}
