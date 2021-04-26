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

import com.sreenathofficial.webcrawler.api.model.dto.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JsoupHtmlLinkFinderTests {

    @Autowired
    private JsoupHtmlLinkFinder jsoupHtmlLinkFinder;

    @Test
    void shouldReturnAllLinksForValidUrlAndHtmlContent() throws Exception {
        Link sourceLink = new Link("https://www.crawler-test.com/mobile/responsive");
        List<Link> links = jsoupHtmlLinkFinder.findChildLinks(sourceLink);
        assertThat(links).isNotNull().hasSize(4);
    }

    @Test
    void shouldReturnEmptyListForInValidUrl() throws Exception {
        Link sourceLink = new Link("https://www.crawler");
        List<Link> links = jsoupHtmlLinkFinder.findChildLinks(sourceLink);
        assertThat(links).isNotNull().isEmpty();
    }

    @Test
    void shouldReturnEmptyListForNonHtmlContent() throws Exception {
        Link sourceLink = new Link("https://cdnjs.cloudflare.com/ajax/libs/javascript.util/0.12.12/javascript.util.min.js");
        List<Link> links = jsoupHtmlLinkFinder.findChildLinks(sourceLink);
        assertThat(links).isNotNull().isEmpty();
    }
}