package com.boc.bocop.sdk.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.oauth.ContainerInfo;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;

/**
 * 
 * @author CindyLiu (atcindyliu@hotmail.com)
 */
public class HttpManager {

	private static final int MAX_RESPONSE_LENGTH = 1024;
	private static final String BOUNDARY = getBoundry();
	private static final String MP_BOUNDARY = "--" + BOUNDARY;
	private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--";

	private static final int SET_CONNECTION_TIMEOUT = 5 * 1000;
	private static final int SET_SOCKET_TIMEOUT = 120 * 1000;

	/**
	 * 
	 * @param url
	 *            服务器地址
	 * @param method
	 *            "GET"or “POST”
	 * @param params
	 *            存放参数的容器
	 * @return 响应结果
	 * @throws com.boc.bocop.sdk.api.exception.BOCOPException
	 */
	public static String openUrl(String url, String method, String params)
			throws BOCOPException {
		String result = "";
		try {
			HttpClient client = getNewHttpClient();
			HttpUriRequest request = null;
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					NetStateManager.getAPN());
			if (method.equals(ParaType.HTTPMETHOD_GET)) {
				HttpGet get = new HttpGet(url);
				request = get;
			} else if (method.equals(ParaType.HTTPMETHOD_POST)) {
				HttpPost post = new HttpPost(url);
				request = post;
				post.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
			} else if (method.equals("DELETE")) {
				request = new HttpDelete(url);
			}
			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();
			if (statusCode != 200) {
				result = readHttpResponse(response);
				throw new BOCOPException(result, statusCode);
			}
			result = readHttpResponse(response);

			return result;
		} catch (IOException e) {
			throw new BOCOPException(e);
		}
	}

	/**
	 * 进行asr http请求
	 * 
	 * @param url
	 *            服务器地址
	 * @param method
	 *            "GET"or “POST”
	 * @param params
	 *            存放参数的容器
	 * @return 响应结果
	 * @throws com.boc.bocop.sdk.api.exception.BOCOPException
	 */
	public static String openUrlAsr(String url, String method,
			Map<String, String> head, Map<String, String> body, int paraType)
			throws BOCOPException, JSONException {
		String result = "";
		try {
			HttpClient client = getNewHttpClient();
			HttpUriRequest request = null;
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					NetStateManager.getAPN());
			if (method.equals(ParaType.HTTPMETHOD_GET)) {
				HttpGet get = new HttpGet(url);
				request = get;

				get.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				get.setHeader("Accept", "application/json");
				get.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					get.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}
				// 封装 JSON 对象
				//JSONObject paramsList = new JSONObject();
				/*for (Map.Entry<String, String> entry : body.entrySet()) {
					get.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}*/
			} else if (method.equals(ParaType.HTTPMETHOD_POST)) {
				HttpPost post = new HttpPost(url);
				request = post;

				post.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					post.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}
				}

				// 封装 JSON 对象
				if(null != body){
				JSONObject params_list = new JSONObject();
				for (Map.Entry<String, String> entry : body.entrySet()) {
					params_list.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}

				// 绑定到请求 Entry
				if (params_list.length() > 0) {
					StringEntity se = new StringEntity(params_list.toString(),
							HTTP.UTF_8);
					post.setEntity(se);
				}
				Logger.d("url ---->" + url + ", body---->" + params_list.toString());
				}

			} else if (method.equals(ParaType.HTTPMETHOD_PUT)) {
				HttpPut put = new HttpPut(url);
				request = put;

				put.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				put.setHeader("Accept", "application/json");
				put.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					put.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}

				// 封装 JSON 对象
				
				JSONObject paramsList = new JSONObject();
				if(null != body){
				for (Map.Entry<String, String> entry : body.entrySet()) {
					paramsList.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}
				}
				Logger.d("body para ------------->" + paramsList.toString());
				
				// 绑定到请求 Entry
				StringEntity se = new StringEntity(paramsList.toString(),
						HTTP.UTF_8);
				put.setEntity(se);

			} else if (method.equals("DELETE")) {
				HttpDelete del  = new HttpDelete(url);
				
				request = del;

				del.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				del.setHeader("Accept", "application/json");
				del.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					del.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}

				// 封装 JSON 对象
				JSONObject paramsList = new JSONObject();
				if(null != body){
				for (Map.Entry<String, String> entry : body.entrySet()) {
					paramsList.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}
			}

			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();

			if (statusCode != 200) {
				result = readHttpResponse(response);
				if( ParaType.OAUTH_CONTAINER == paraType ){
					saveCookies(response);
				} else if (ParaType.OAUTH_REGISTER == paraType) {
					saveCookies(response);
				}
				else if( ParaType.OAUTH_DEL == paraType){
					delCookies();
				}
				throw new BOCOPException(result, statusCode);
			}
			result = readHttpResponse(response);
			if( ParaType.OAUTH_CONTAINER == paraType ){
				saveCookies(response);
			}else if (ParaType.OAUTH_REGISTER == paraType) {
				saveCookies(response);
			}
			else if( ParaType.OAUTH_DEL == paraType){
				delCookies();
			}
			return result;
		} catch (IOException e) {
			throw new BOCOPException(e);
		}
	}
	
	public static String openUrlSap(String url, String method,
			Map<String, String> head, Map<String, String> body)
			throws BOCOPException, JSONException {
		String result = "";
		try {
			HttpClient client = getNewHttpClient();
			HttpUriRequest request = null;
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					NetStateManager.getAPN());
			if (method.equals(ParaType.HTTPMETHOD_GET)) {
				HttpGet get = new HttpGet(url);
				request = get;

				get.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				get.setHeader("Accept", "application/json");
				get.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					get.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}
				// 封装 JSON 对象
				//JSONObject paramsList = new JSONObject();
				/*for (Map.Entry<String, String> entry : body.entrySet()) {
					get.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}*/
			} else if (method.equals(ParaType.HTTPMETHOD_POST)) {
				HttpPost post = new HttpPost(url);
				request = post;

				post.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					post.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}
				}

				// 封装 JSON 对象
				if(null != body){
				JSONObject params_list = new JSONObject();
				for (Map.Entry<String, String> entry : body.entrySet()) {
					params_list.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}

				// 绑定到请求 Entry
				if (params_list.length() > 0) {
					StringEntity se = new StringEntity(params_list.toString(),
							HTTP.UTF_8);
					post.setEntity(se);
				}
				Logger.d("url ---->" + url + ", body---->" + params_list.toString());
				}

			} else if (method.equals(ParaType.HTTPMETHOD_PUT)) {
				HttpPut put = new HttpPut(url);
				request = put;

				put.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				put.setHeader("Accept", "application/json");
				put.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					put.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}

				// 封装 JSON 对象
				
				JSONObject paramsList = new JSONObject();
				if(null != body){
				for (Map.Entry<String, String> entry : body.entrySet()) {
					paramsList.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}
				}
				Logger.d("body para ------------->" + paramsList.toString());
				
				// 绑定到请求 Entry
				StringEntity se = new StringEntity(paramsList.toString(),
						HTTP.UTF_8);
				put.setEntity(se);

			} else if (method.equals("DELETE")) {
				HttpDelete del  = new HttpDelete(url);
				
				request = del;

				del.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				del.setHeader("Accept", "application/json");
				del.setHeader("Content-type", "application/json");

				if(null != head){
				for (Map.Entry<String, String> entry : head.entrySet()) {
					del.addHeader(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}

				// 封装 JSON 对象
				JSONObject paramsList = new JSONObject();
				if(null != body){
				for (Map.Entry<String, String> entry : body.entrySet()) {
					paramsList.put(entry.getKey(),
							StringUtil.null2Blank(entry.getValue()));
				}}
			}

			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();

			if (statusCode != 200) {
				result = readHttpResponse(response);
				throw new BOCOPException(result, statusCode);
			}
			result = readHttpResponse(response);
			return result;
		} catch (IOException e) {
			throw new BOCOPException(e);
		}
	}
	
	/**
	 * 保存Cookie
	 * @param httpResponse
	 */
	private static void saveCookies(HttpResponse httpResponse)
	{
		Header[] headers = httpResponse.getHeaders("Set-Cookie");
		if (headers == null)
			return;
		String value = "";
		for(int i=0;i<headers.length;i++)
		{
			String cookie=headers[i].getValue();
			//modified by liuweina,用";"而不是":"拆分cookie，解决了登录后报“登录失效”的bug
			String[]cookievalues=cookie.split(";");

			//String[] keyPair=cookievalues[0].split("");
			//String key=keyPair[0].trim();
			value=cookievalues.length>1?cookievalues[0].trim():"";

		}
		//CookieContiner.put(key, value);
		Logger.d("cookie value ------->" + value);
		ContainerInfo.setSessionCookie(value);
	}
	
	private static void delCookies()
	{
		ContainerInfo.setSessionCookie("");
	}

	private static HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(params, 10000);
			HttpConnectionParams.setSoTimeout(params, 10000);

			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), Constants.httpPort));
			registry.register(new Scheme("https", sf, Constants.httpsPort));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			HttpConnectionParams.setConnectionTimeout(params,
					SET_CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, SET_SOCKET_TIMEOUT);
			HttpClient client = new DefaultHttpClient(ccm, params);
			return client;
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	private static class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}

	private static void paramToUpload(OutputStream baos, BOCOPParameters params)
			throws BOCOPException {
		String key = "";
		for (int loc = 0; loc < params.size(); loc++) {
			key = params.getKey(loc);
			StringBuilder temp = new StringBuilder(10);
			temp.setLength(0);
			temp.append(MP_BOUNDARY).append("\r\n");
			temp.append("content-disposition: form-data; name=\"").append(key)
					.append("\"\r\n\r\n");
			temp.append(params.getValue(key)).append("\r\n");
			byte[] res = temp.toString().getBytes();
			try {
				baos.write(res);
			} catch (IOException e) {
				throw new BOCOPException(e);
			}
		}
	}

	private static void imageContentToUpload(OutputStream out, String imgpath)
			throws BOCOPException {
		if (imgpath == null) {
			return;
		}
		StringBuilder temp = new StringBuilder();

		temp.append(MP_BOUNDARY).append("\r\n");
		temp.append("Content-Disposition: form-data; name=\"pic\"; filename=\"")
				.append("news_image").append("\"\r\n");
		String filetype = "image/png";
		temp.append("Content-Type: ").append(filetype).append("\r\n\r\n");
		byte[] res = temp.toString().getBytes();
		FileInputStream input = null;
		try {
			out.write(res);
			input = new FileInputStream(imgpath);
			byte[] buffer = new byte[1024 * 50];
			while (true) {
				int count = input.read(buffer);
				if (count == -1) {
					break;
				}
				out.write(buffer, 0, count);
			}
			out.write("\r\n".getBytes());
			out.write(("\r\n" + END_MP_BOUNDARY).getBytes());
		} catch (IOException e) {
			throw new BOCOPException(e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					throw new BOCOPException(e);
				}
			}
		}
	}

	/**
	 * 读取HttpResponse数据
	 * 
	 * @param response
	 * @return
	 */
	private static String readHttpResponse(HttpResponse response) {
		String result = "";
		HttpEntity entity = response.getEntity();
		InputStream inputStream;
		try {
			inputStream = entity.getContent();
			ByteArrayOutputStream content = new ByteArrayOutputStream();

			Header header = response.getFirstHeader("Content-Encoding");
			if (header != null
					&& header.getValue().toLowerCase().indexOf("gzip") > -1) {
				inputStream = new GZIPInputStream(inputStream);
			}

			int readBytes = 0;
			byte[] sBuffer = new byte[MAX_RESPONSE_LENGTH];
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
				content.write(sBuffer, 0, readBytes);
			}
			result = new String(content.toByteArray());
			return result;
		} catch (IllegalStateException e) {
		} catch (IOException e) {
		}
		return result;
	}

	/**
	 * 产生11位的boundary
	 */
	static String getBoundry() {
		StringBuffer sbuffer = new StringBuffer();
		for (int t = 1; t < 12; t++) {
			long time = System.currentTimeMillis() + t;
			if (time % 3 == 0) {
				sbuffer.append((char) time % 9);
			} else if (time % 3 == 1) {
				sbuffer.append((char) (65 + time % 26));
			} else {
				sbuffer.append((char) (97 + time % 26));
			}
		}
		return sbuffer.toString();
	}

}
