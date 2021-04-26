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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;

@Service
public class URILinkDomainMatcher implements LinkDomainMatcher {

    Logger logger = LoggerFactory.getLogger(URILinkDomainMatcher.class);

    /**
     * @param link
     * @param otherLink
     * @return
     */
    @Override
    public boolean match(Link link, Link otherLink) {
        try {
            if(link==null || otherLink==null){
                throw new Exception("Cannot match null references");
            }

            if(!StringUtils.hasText(link.getUrl())){
                throw new Exception("Cannot match empty url");
            }

            if(!StringUtils.hasText(otherLink.getUrl())){
                throw new Exception("Cannot match empty url");
            }

            final URI linkUri = new URI(link.getUrl());
            final URI otherLinkUri = new URI(otherLink.getUrl());

            final String linkDomain = linkUri.getHost();
            final String otherLinkDomain = otherLinkUri.getHost();

            if(StringUtils.hasText(linkDomain)
                    && linkDomain.equalsIgnoreCase(otherLinkDomain)){
                return true;
            }


        }catch(Exception e){
            logger.error("Error while matching domains: {}", e.getMessage());
        }

        return false;
    }
}
