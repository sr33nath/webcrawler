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

package com.sreenathofficial.webcrawler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebcrawlerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturn200andChildLinksAsJson() throws Exception {
		this.mockMvc.perform(get("/crawl?url=https://www.crawler-test.com/mobile/responsive"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"url\":\"https://www.crawler-test.com/mobile/responsive\",\"childLinks\":[{\"url\":\"/\",\"childLinks\":null},{\"url\":\"/css/app.css\",\"childLinks\":null},{\"url\":\"/favicon.ico?r=1.6\",\"childLinks\":null},{\"url\":\"/bower_components/jquery/jquery.min.js\",\"childLinks\":null}]}")));
	}

	@Test
	void shouldReturn400andMalformedURLMessage() throws Exception {
		this.mockMvc.perform(get("/crawl?url=https"))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Validation failed: Malformed URL:")));
	}

	@Test
	void shouldReturn400andParamRequiredMessage() throws Exception {
		this.mockMvc.perform(get("/crawl"))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Required request parameter")));
	}

	@Test
	void shouldReturn404andNotFoundMessage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().string(containsString("No handler found")));
	}

}
