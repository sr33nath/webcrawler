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

import com.sreenathofficial.webcrawler.api.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CrawlApiControllerTests {

    @Autowired
    private CrawlerApiController crawlerApiController;

    @Test
    void shouldThrowValidationException(){
        Exception e = assertThrows(ValidationException.class, () -> {
            crawlerApiController.crawl("https://www.");
        });

        assertThat(e.getMessage()).containsIgnoringCase("Validation failed");
    }

    @Test
    void shouldReturnJsonStringWithAllLinks(){
        String linksJson = "";
        try {
            linksJson = crawlerApiController.crawl("https://www.crawler-test.com/mobile/responsive");
        }catch (Exception e){}

        assertThat(linksJson).isEqualTo("{\"url\":\"https://www.crawler-test.com/mobile/responsive\",\"childLinks\":[{\"url\":\"/\",\"childLinks\":null},{\"url\":\"/css/app.css\",\"childLinks\":null},{\"url\":\"/favicon.ico?r=1.6\",\"childLinks\":null},{\"url\":\"/bower_components/jquery/jquery.min.js\",\"childLinks\":null}]}");
    }
}
