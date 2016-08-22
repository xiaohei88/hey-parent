package org.heyframework.common.http;

import org.apache.http.impl.client.CloseableHttpClient;

public class HttpsRequestPost extends HttpRequestPost {

	public HttpsRequestPost(String url) {
		super(url);
	}

	@Override
	public CloseableHttpClient getHttpClient() {
		return HttpsCliects.createDefault();
	}
}
