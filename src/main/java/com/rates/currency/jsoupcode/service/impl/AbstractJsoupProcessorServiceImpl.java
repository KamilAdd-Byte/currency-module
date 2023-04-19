package com.rates.currency.jsoupcode.service.impl;

import org.springframework.stereotype.Service;
import com.rates.currency.jsoupcode.AbstractJsoupProcessor;
import java.util.List;

@Service
public class AbstractJsoupProcessorServiceImpl {

    public List<String> allCodesWithWikipedia() {
        return AbstractJsoupProcessor.allCodesIso4217();
    }
}
