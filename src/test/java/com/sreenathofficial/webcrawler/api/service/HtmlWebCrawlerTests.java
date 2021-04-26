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

import com.sreenathofficial.webcrawler.api.model.dto.CrawlMetadata;
import com.sreenathofficial.webcrawler.api.model.dto.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HtmlWebCrawlerTests {

    @Autowired
    private HtmlWebCrawler htmlWebCrawler;

    @Test
    void shouldReturnAllLinksAfterCrawlingHtmlContent() throws Exception {
        final CrawlMetadata crawlMetadata = htmlWebCrawler.crawl("https://www.crawler-test.com/mobile/responsive");

        List<Link> childLinks = crawlMetadata.getRootLink().getChildLinks();
        Set<String> discoveredUrls = crawlMetadata.getDiscoveredUrls();

        assertThat(childLinks).isNotNull().hasSize(4);
        assertThat(discoveredUrls).contains("https://www.crawler-test.com/mobile/responsive",
                "/",
                "/css/app.css",
                "/favicon.ico?r=1.6",
                "/bower_components/jquery/jquery.min.js");
    }

    @Test
    void shouldReturnNoChildLinksOnCrawlingEmptyWebPage() throws Exception {
        final CrawlMetadata crawlMetadata = htmlWebCrawler.crawl("https://www.webpagetest.org/blank.html");

        List<Link> childLinks = crawlMetadata.getRootLink().getChildLinks();
        Set<String> discoveredUrls = crawlMetadata.getDiscoveredUrls();

        assertThat(childLinks).isEmpty();
        assertThat(discoveredUrls).contains("https://www.webpagetest.org/blank.html");
    }
}
