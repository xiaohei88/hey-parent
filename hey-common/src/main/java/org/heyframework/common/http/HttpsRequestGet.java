package org.heyframework.common.http;

import org.apache.http.impl.client.CloseableHttpClient;

public class HttpsRequestGet extends HttpRequestGet {

	public HttpsRequestGet(String url) {
		super(url);
	}

	@Override
	public CloseableHttpClient getHttpClient() {
		return HttpsCliects.createDefault();
	}
}
