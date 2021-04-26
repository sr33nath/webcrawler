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

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class JsoupWebContentDownloader implements WebContentDownloader {

    Logger logger = LoggerFactory.getLogger(JsoupWebContentDownloader.class);

    /**
     * @param url
     * @return
     */
    @Override
    public String getHtmlAsString(final String url) {
        try {

            final Connection.Response resp = Jsoup.connect(url).execute();

            if(StringUtils.hasText(resp.contentType())
                    && resp.contentType().toLowerCase().contains(HTML_CONTENT_TYPE)){
                return resp.body();
            }else{
                logger.info("Content-type is non-html for URL: {}", url);
            }

        } catch (IOException ioe){
            logger.error("Unable to connect to URL: {}, error: {}", url, ioe.getMessage());
        } catch (Exception e){
            logger.error("Error while connecting to URL: {}, error: {}", url, e.getMessage());
        }

        return null;
    }

}
