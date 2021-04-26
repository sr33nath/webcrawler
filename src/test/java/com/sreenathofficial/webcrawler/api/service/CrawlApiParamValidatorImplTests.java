/*
 * Copyright (c) 2021 sreenathofficial.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sreenathofficial.webcrawler.api.service;

import com.sreenathofficial.webcrawler.api.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class CrawlApiParamValidatorImplTests {

    @Autowired
    private CrawlApiParamValidatorImpl crawlApiParamValidatorImpl;

    @Test
    void shouldReturnTrueForValidUrlWithHtmlContentType() throws Exception {
        final boolean valid = crawlApiParamValidatorImpl.validate("https://www.crawler-test.com/");

        assertThat(valid).isTrue();
    }

    @Test
    void shouldReturnFalseForInValidUrl() throws Exception {
        Exception e = assertThrows(ValidationException.class, () -> {
            crawlApiParamValidatorImpl.validate("https://www.");
        });

        assertThat(e.getMessage()).containsIgnoringCase("Validation failed");
    }

    @Test
    void shouldReturnFalseForValidUrlWithNonHtmlContentType() throws Exception {
        Exception e = assertThrows(ValidationException.class, () -> {
            crawlApiParamValidatorImpl.validate("https://cdnjs.cloudflare.com/ajax/libs/javascript.util/0.12.12/javascript.util.min.js");
        });

        assertThat(e.getMessage()).containsIgnoringCase("Unhandled content type");
    }
}
