package com.fulicent.common.utils;

import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.exception.ApiException;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.fulicent.common.entity.ApiResponseStatus.SYSTEM_CONNECTION_ERROR;

public class HttpRequestUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    private static CloseableHttpClient httpClient;

    private static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            synchronized (HttpRequestUtils.class) {
                if (httpClient == null) {
                    try {
                        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
                        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                        httpClient = HttpClientBuilder.create()
                                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                                .setSSLSocketFactory(sslsf)
                                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36")
                                .build();
                    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                        throw new ApiException(ApiResponseStatus.SYSTEM_INTERNAL_ERROR, "Init http client error", e);
                    }
                }
            }
        }
        return httpClient;
    }

    public static <T> T get(String url, Map<String, String> headers, Function<String, T> success, Function<String, T> fail) {
        HttpGet httpGet = new HttpGet(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpGet.setHeaders(headerArray);
        return doExecute(httpGet, success, fail);
    }

    public static <T> T post(String url, Map<String, String> headers, String body, Function<String, T> success, Function<String, T> fail) {
        HttpPost httpPost = new HttpPost(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpPost.setHeaders(headerArray);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return doExecute(httpPost, success, fail);
    }

    public static <T> T post(String url, Map<String, String> headers, Map<String, String> form, Function<String, T> success, Function<String, T> fail) {
        HttpPost httpPost = new HttpPost(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpPost.setHeaders(headerArray);
        List<NameValuePair> formData = form.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        httpPost.setEntity(new UrlEncodedFormEntity(formData, Consts.UTF_8));
        return doExecute(httpPost, success, fail);
    }

    public static <T> T post(String url, Map<String, String> headers, String body, BiFunction<String, HttpClientContext, T> success, Function<String, T> fail) {
        HttpPost httpPost = new HttpPost(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpPost.setHeaders(headerArray);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return doExecute(httpPost, success, fail);
    }

    public static <T> T put(String url, Map<String, String> headers, String body, Function<String, T> success, Function<String, T> fail) {
        HttpPut httpPut = new HttpPut(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpPut.setHeaders(headerArray);
        httpPut.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return doExecute(httpPut, success, fail);
    }

    public static <T> T delete(String url, Map<String, String> headers, Function<String, T> success, Function<String, T> fail) {
        HttpDelete httpDelete = new HttpDelete(url);
        Header[] headerArray = headers.entrySet().stream().map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList()).toArray(new Header[headers.size()]);
        httpDelete.setHeaders(headerArray);
        return doExecute(httpDelete, success, fail);
    }

    private static <T> T doExecute(HttpRequestBase httpRequestBase, Function<String, T> success, Function<String, T> fail) {
        long t1 = System.currentTimeMillis();
        String response = "";
        try (CloseableHttpResponse closeableHttpResponse = getHttpClient().execute(httpRequestBase)) {
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, "UTF-8");
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return success.apply(response);
            } else {
                return fail.apply(response);
            }
        } catch (IOException e) {
            throw new ApiException(SYSTEM_CONNECTION_ERROR, "", e);
        } finally {
            logger.info("event_type=\"Http Request\", url=\"{}\", message=\"{}\", method=\"{}\", take=\"{}\" ",
                    "Http Request",
                    httpRequestBase.getURI().toString(),
                    response,
                    httpRequestBase.getMethod(),
                    (System.currentTimeMillis() - t1));
        }
    }

    private static <T> T doExecute(HttpRequestBase httpRequestBase, BiFunction<String, HttpClientContext, T> success, Function<String, T> fail) {
        long t1 = System.currentTimeMillis();
        HttpClientContext context = HttpClientContext.create();
        String response = "";
        try (CloseableHttpResponse closeableHttpResponse = getHttpClient().execute(httpRequestBase, context)) {
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, "UTF-8");
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return success.apply(response, context);
            } else {
                return fail.apply(response);
            }
        } catch (IOException e) {
            throw new ApiException(SYSTEM_CONNECTION_ERROR, "", e);
        } finally {
            logger.info("event_type=\"Http Request\", url=\"{}\", message=\"{}\", method=\"{}\", take=\"{}\"",
                    httpRequestBase.getURI().toString(),
                    httpRequestBase.getMethod(),
                    response,
                    (System.currentTimeMillis() - t1));
        }
    }
}
