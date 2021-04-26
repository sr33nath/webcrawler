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
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class URILinkDomainMatcherTests {

    @Autowired
    private URILinkDomainMatcher uRILinkDomainMatcher;

    @Test
    void shouldReturnTrueForMatchingDomains() throws Exception {
        final Link link = new Link("https://www.crawler-test.com/");
        final Link otherLink = new Link("https://www.crawler-test.com/titles/empty_title");

        boolean domainMatches = uRILinkDomainMatcher.match(link, otherLink);

        assertThat(domainMatches).isTrue();
    }

    @Test
    void shouldReturnFalseForNonMatchingDomains() throws Exception {
        final Link link = new Link("https://www.crawler-test.com/");
        final Link otherLink = new Link("https://www.google.com/titles/empty_title");

        boolean domainMatches = uRILinkDomainMatcher.match(link, otherLink);

        assertThat(domainMatches).isFalse();
    }

    @Test
    void shouldReturnFalseForNullArgs() throws Exception {
        final Link link1 = new Link(null);
        final Link otherLink1 = new Link("https://www.google.com/titles/empty_title");
        final Link link2 = new Link("https://www.crawler-test.com/");
        final Link otherLink2 = new Link(null);
        final Link link3 = new Link(null);
        final Link otherLink3 = new Link(null);

        boolean domainMatches1 = uRILinkDomainMatcher.match(link1, otherLink1);
        boolean domainMatches2 = uRILinkDomainMatcher.match(link2, otherLink2);
        boolean domainMatches3 = uRILinkDomainMatcher.match(link3, otherLink3);

        assertThat(domainMatches1).isFalse();
        assertThat(domainMatches2).isFalse();
        assertThat(domainMatches3).isFalse();
    }
}
