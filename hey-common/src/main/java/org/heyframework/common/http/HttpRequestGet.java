package org.heyframework.common.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;
import org.heyframework.common.util.MapUtils;
import org.heyframework.common.util.StringUtils;

public class HttpRequestGet extends AbstractHttpRequest {

	public HttpRequestGet(String url) {
		super(url);
	}

	@Override
	public String execute() throws ClientProtocolException, IOException {
		return reExecute(null);
	}

	@Override
	public String reExecute(String url) throws ClientProtocolException, IOException {
		if (httpclient != null) {
			CloseableHttpResponse response = null;
			try {
				if (!StringUtils.isEmpty(url)) {
					this.url = url;
				}
				// 1、设置参数
				List<NameValuePair> nvps = getNameValuePairs();
				String params = URLEncodedUtils.format(nvps, "UTF-8");
				params = StringUtils.isEmpty(params) ? "" : "?" + params;
				HttpGet httpGet = new HttpGet(this.url + params);

				// 2、设置头参数
				if (!MapUtils.isEmpty(headerMap)) {
					for (Map.Entry<String, String> header : headerMap.entrySet()) {
						httpGet.addHeader(header.getKey(), header.getValue());
					}
				}

				// 3、设置请求和传输超时时间
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(6000)
						.build();
				httpGet.setConfig(requestConfig);

				// 4、开始请求
				response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();

				// 5、响应信息
				String result = entity == null ? "" : EntityUtils.toString(entity);

				// 6、关闭数据流入口
				EntityUtils.consume(entity);
				return result;
			} finally {
				if (response != null) {
					response.close();
				}
			}
		}
		return "";
	}
}
