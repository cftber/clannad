package com.tgzhao.clannad.common.httpclient;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class HttpClientApp {

    public static void main(String[] args) throws URISyntaxException {
        // 1.
        // HttpClient 提供很多工具方法来简化创建和修改执行 URI。
        // URI 也可以编程来拼装
        URI uri = null;
        try {
            uri = URIUtils.createURI("http", "www.google.com", -1,
                    "/search",
                    "q=httpclient&btnG=Google+Search&aq=f&oq=", null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

        // 2.
        // 查询字符串也可以从独立的参数中来生成：
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("q", "httpclient"));
        qparams.add(new BasicNameValuePair("btnG", "Google Search"));
        qparams.add(new BasicNameValuePair("aq", "f"));
        qparams.add(new BasicNameValuePair("oq", null));
        URI uri2 = URIUtils.createURI("http", "www.google.com", -1,
                "/search",
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        HttpGet httpget2 = new HttpGet(uri2);
        System.out.println(httpget2.getURI());
    }

    public static void httpEntityTest() throws IOException {
        //result
//        Content-Type: text/plain; charset=UTF-8
//        17
//        UTF-8
//        important message
//        17
        StringEntity myEntity = new StringEntity("important message",
                "UTF-8");
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.getContentCharSet(myEntity));
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);


        StringEntity entity = new StringEntity("important message",
                "text/plain; charset=\"UTF-8\"");
        //通知 HttpClient 分块编码的首选
        entity.setChunked(true);
        HttpPost httppost = new
                HttpPost("http://localhost/acrtion.do");
        httppost.setEntity(entity);
    }

    /**
     *
     * 当完成一个响应实体， 那么保证所有实体内容已经被完全消耗是很重要的， 所以连接可
     以安全的放回到连接池中， 而且可以通过连接管理器对后续的请求重用连接。 处理这个操作
     的最方便的方法是调用 HttpEntity#consumeContent() 方法来消耗流中的任意可用
     内容
     * @throws IOException
     */
    public static void consumeContent() throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://localhost/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            int byteOne = instream.read();
            int byteTwo = instream.read();
            // if Do not need the rest
            httpget.abort();

        }
    }

    /**
     *控制响应的最简便和最方便的方式是使用 ResponseHandler 接口。 这个放完完全减
     轻了用户关于连接管理的担心。当使用 ResponseHandler 时，HttpClient 将会自动关注
     并保证释放连接到连接管理器中去，而不管请求执行是否成功或引发了异常
     * @throws IOException
     */
    public static void responseHandlerTest() throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://localhost/");
        ResponseHandler<byte[]> handler = new
                ResponseHandler<byte[]>() {
                    public byte[] handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            return EntityUtils.toByteArray(entity);
                        } else {
                            return null;
                        }
                    }
                };
        byte[] response = httpclient.execute(httpget, handler);
    }

    public static void httpRequestRetryHandler() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpRequestRetryHandler myRetryHandler = new
                HttpRequestRetryHandler() {
                    public boolean retryRequest(IOException exception,
                                                int executionCount, HttpContext context) {
                        if (executionCount >= 5) {
                            // 如果超过最大重试次数，那么就不要继续了
                            return false;
                        }
                        if (exception instanceof NoHttpResponseException) {
                            // 如果服务器丢掉了连接，那么就重试
                            return true;
                        }
                        if (exception instanceof SSLHandshakeException) {
                            // 不要重试SSL握手异常
                            return false;
                        }
                        HttpRequest request = (HttpRequest) context.getAttribute(
                                ExecutionContext.HTTP_REQUEST);
                        boolean idempotent = !(request instanceof
                                HttpEntityEnclosingRequest);
                        if (idempotent) {
                            // 如果请求被认为是幂等的，那么就重试
                            return true;
                        }
                        return false;
                    }
                };
        httpclient.setHttpRequestRetryHandler(myRetryHandler);
    }
}
