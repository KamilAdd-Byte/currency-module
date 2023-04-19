package com.rates.currency.scrapp;

import com.rates.currency.exception.DocumentCreateException;
import com.rates.currency.message.LogMessage;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DocumentCreator {

    public Document create (String baseUri) {
        return Try.of(()-> createNewDocument(baseUri))
                .onSuccess(doc -> log.info(LogMessage.documentCreatedMessage()))
                .getOrElseThrow(DocumentCreateException::becauseDocumentNotCreated);
    }

    private Document createNewDocument(String baseUri) throws IOException {
        log.debug("Starting connect jsoup to uri [{}]", baseUri);
        return Jsoup.connect(baseUri).get();
    }
}
