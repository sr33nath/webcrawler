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

package com.sreenathofficial.webcrawler.api.controller;

import com.sreenathofficial.webcrawler.api.exception.CrawlApiException;
import com.sreenathofficial.webcrawler.api.exception.ValidationException;
import com.sreenathofficial.webcrawler.api.model.dto.CrawlMetadata;
import com.sreenathofficial.webcrawler.api.service.CrawlApiParamValidator;
import com.sreenathofficial.webcrawler.api.service.WebCrawler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class CrawlerApiController {

    Logger logger = LoggerFactory.getLogger(CrawlerApiController.class);

    @Autowired
    private WebCrawler crawler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CrawlApiParamValidator crawlApiValidator;

    /**
     * @param url
     * @return
     * @throws ValidationException
     * @throws CrawlApiException
     */
    @RequestMapping( path = "/crawl", method = RequestMethod.GET)
    public String crawl(@RequestParam("url") String url) throws ValidationException, CrawlApiException {

        try {

            if(crawlApiValidator.validate(url)) {

                final CrawlMetadata crawlMetadata = crawler.crawl(url);

                return objectMapper.writeValueAsString(crawlMetadata.getRootLink());

            }

        } catch(ValidationException ve){
            throw ve;
        } catch(Exception e){
            logger.error("Crawl API failed: {}", e.getMessage());
            throw new CrawlApiException(e.getMessage());
        }

        return "{}";
    }
}
