package com.tgzhao.clannad.common.httpclient.util;


import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class HttpWrapper {

//    private static final Logger logger = LoggerFactory.getLogger(HttpWrapper.class);

    private static HttpWrapper _instance = new HttpWrapper();

    private HttpWrapper() {
        createHttpClient();
    }

    public static HttpWrapper getInstance() {
        return  _instance;
    }

    private int httpClientTimeout = 5000;

    private static CloseableHttpClient closeableHttpClient;

    private void silentClose(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String url) {

        HttpGet httpGet = new HttpGet();
        HttpEntity responseEntity = null;
        CloseableHttpResponse response = null;

        try {
            httpGet.setURI(new URI(url));

            response = closeableHttpClient.execute(httpGet);
            responseEntity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(responseEntity, "UTF-8");
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consumeQuietly(responseEntity);
            silentClose(response);
        }

        return null;
    }

    public String postJson(String url, String jsonData, int timeout) {
        HttpPost httpPost = new HttpPost();
        HttpEntity responseEntity = null;
        CloseableHttpResponse response = null;
        try {
            httpPost.setURI(new URI(url));

            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
            httpPost.setConfig(config);

            StringEntity entity = new StringEntity(jsonData, ContentType.create("application/json", Charsets.UTF_8));
            httpPost.setEntity(entity);
            response = closeableHttpClient.execute(httpPost);
            responseEntity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(responseEntity, "UTF-8");
            }


        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consumeQuietly(responseEntity);
            silentClose(response);
        }
        return null;
    }

    private void createHttpClient() {
        ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(Charsets.toCharset("UTF-8")).build();

        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setDefaultConnectionConfig(connConfig);
        // 将最大连接数增加到200
        clientConnectionManager.setMaxTotal(200);
        // // 将每个路由基础的连接增加到20
        clientConnectionManager.setDefaultMaxPerRoute(20);
        // //将目标主机的最大连接数增加到50
        // HttpHost localhost = new HttpHost("xxx", 443);
        // clientConnectionManager.setMaxPerRoute(new HttpRoute(localhost), 50);

        // 设置超时时间
        RequestConfig config = RequestConfig.custom().setConnectTimeout(httpClientTimeout)
                .setConnectionRequestTimeout(httpClientTimeout).setSocketTimeout(httpClientTimeout).build();

        closeableHttpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setConnectionManager(clientConnectionManager)
                .build();
    }
}
