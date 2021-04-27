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

package com.sreenathofficial.webcrawler.api.model.dto;

import java.util.Set;

/**
 * CrawlMetadata class holds data relevant to perform a single crawl
 */
public class CrawlMetadata {

    /**
     * Starting url for the crawl.
     * Newly discovered urls from this Link would be added to rootLink.childLinks
     */
    private Link rootLink;


    /**
     * Used to store already crawled URLs so that no duplication happens
     */
    private Set<String> discoveredUrls;

    public CrawlMetadata(Link rootLink, Set<String> discoveredUrls){
        this.rootLink = rootLink;
        this.discoveredUrls = discoveredUrls;
    }

    public Set<String> getDiscoveredUrls() {
        return discoveredUrls;
    }

    public Link getRootLink() {
        return rootLink;
    }
}
