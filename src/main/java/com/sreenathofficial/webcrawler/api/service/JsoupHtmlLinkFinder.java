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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JsoupHtmlLinkFinder contains logic to parse URLs or links present in an HTML page
 * Jsoup library is used to accomplish this.
 */
@Service
public class JsoupHtmlLinkFinder implements HtmlLinkFinder {

    Logger logger = LoggerFactory.getLogger(JsoupHtmlLinkFinder.class);

    @Autowired
    private WebContentDownloader webContentDownloader;

    /**
     * Method accepts single Link object with single url
     * @param link
     * Downloads the html content from that url
     * Uses Jsoup library to find
     * href from anchor tags
     * href from link tags
     * src from img tags
     * src from script tags
     * Add the found values to List of Link objects
     * @return List of Link objects
     */
    @Override
    public List<Link> findChildLinks(Link link) {

        List<Link> links = new ArrayList<>();

        try {

            final String htmlContent = webContentDownloader.getHtmlAsString(link.getUrl());

            if(StringUtils.hasText(htmlContent)) {

                final Document doc = Jsoup.parse(htmlContent);

                doc.select("a[href]").forEach(s -> {
                    links.add(new Link(s.attr("href")));
                });

                doc.select("link[href]").forEach(s -> {
                    links.add(new Link(s.attr("href")));
                });

                doc.select("img[src]").forEach(s -> {
                    links.add(new Link(s.attr("src")));
                });

                doc.select("script[src]").forEach(s -> {
                    links.add(new Link(s.attr("src")));
                });

            }

        } catch (Exception e){
            logger.error("Error while parsing html from URL: " + link.getUrl(), e);
        }

        return links;
    }
}
