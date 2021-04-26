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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JsoupWebContentDownloaderTests {

    @Autowired
    private JsoupWebContentDownloader jsoupWebContentDownloader;

    @Test
    void shouldReturnValidTextContentForValidUrlAndContentType() throws Exception {
        final String webContent = jsoupWebContentDownloader.getHtmlAsString("https://www.crawler-test.com/");

        assertThat(webContent)
                .isNotNull()
                .containsIgnoringCase("<html>")
                .containsIgnoringCase("<body>");
    }

    @Test
    void shouldReturnNullForInvalidUrl() throws Exception {
        final String webContent = jsoupWebContentDownloader.getHtmlAsString("https://www.crawler");

        assertThat(webContent).isNull();;
    }

    @Test
    void shouldReturnNullForValidUrlAndNonHtmlContentType() throws Exception {
        final String webContent = jsoupWebContentDownloader.getHtmlAsString("https://cdnjs.cloudflare.com/ajax/libs/javascript.util/0.12.12/javascript.util.min.js");

        assertThat(webContent).isNull();
    }
}
