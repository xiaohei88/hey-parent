package org.heyframework.common.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.heyframework.common.util.MapUtils;
import org.heyframework.common.util.StringUtils;

public class HttpRequestPost extends AbstractHttpRequest {

	public HttpRequestPost(String url) {
		super(url);
	}

	/** 请求内容 */
	private String content;

	@Override
	public String execute() throws ClientProtocolException, IOException {
		return reExecute(null);
	}

	@Override
	public String reExecute(String url) throws ClientProtocolException, IOException {
		if (httpclient != null) {
			CloseableHttpResponse response = null;
			try {
				// 1、设置URL及参数
				if (!StringUtils.isEmpty(url)) {
					this.url = url;
				}
				HttpPost httpPost = new HttpPost(this.url);
				List<NameValuePair> nvps = getNameValuePairs();
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
				httpPost.setEntity(new StringEntity(content, Charset.forName("UTF-8")));

				// 2、设置头参数
				if (!MapUtils.isEmpty(headerMap)) {
					for (Map.Entry<String, String> header : headerMap.entrySet()) {
						httpPost.addHeader(header.getKey(), header.getValue());
					}
				}

				// 3、开始请求
				response = httpclient.execute(httpPost);
				HttpEntity entity = response.getEntity();

				// 4、响应信息
				String result = entity == null ? "" : EntityUtils.toString(entity);

				// 5、关闭数据流入口
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
