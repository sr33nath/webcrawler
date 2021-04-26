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
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;

@Service
public class CrawlApiParamValidatorImpl implements CrawlApiParamValidator{

    Logger logger = LoggerFactory.getLogger(CrawlApiParamValidatorImpl.class);

    /**
     * @param url
     * @return
     * @throws ValidationException
     */
    @Override
    public boolean validate(String url) throws ValidationException {

        try {

            URI uri = new URI(url);

            final Connection.Response resp = Jsoup.connect(url).execute();

            if(!StringUtils.hasText(resp.contentType())
                    || !resp.contentType().toLowerCase().contains(WebContentDownloader.HTML_CONTENT_TYPE)){
                throw new Exception("Unsupported content type");
            }

            return true;

        } catch (Exception e){
            logger.error("Validation exception: {}", e.getMessage());
            throw new ValidationException(e.getMessage());
        }

    }

}
