package com.wentjiang.crawler.http;

import com.wentjiang.crawler.http.instant.HttpClientPoolIdleMonitor;
import com.wentjiang.crawler.http.instant.HttpClientPoolIdleMonitorTask;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jiangwentao on 12/5/2016 3:59 PM.
 */
@Slf4j
public class HttpClientPool {
    private PoolingHttpClientConnectionManager httpConnManager = null;
    @Getter
    private CloseableHttpClient httpClient = null;
    private int maxConnection = 100;
    private int maxConnectionPerRoute = 50;
    private PoolingHttpClientConnectionManager httpsCertConnManager = null;
    private CloseableHttpClient httpsCertClient = null;
    private int httpsMaxConnection = 100;
    private int httpsMaxConnectionPerRoute = 50;
    private String httpsCertFilePath = null;
    private String httpsCertKeystoreType = "jks";
    private String httpsCertPasswd = null;
    private int keepAliveMillSec = 30000;

    public HttpClientPool(int maxConnection, int maxConnectionPerRoute) {
        this.initConfig(maxConnection, maxConnectionPerRoute, 0, 0, (String) null, (String) null, (String) null, 0);
        this.initHttpClient();
    }

    public HttpClientPool(int maxConnection, int maxConnectionPerRoute, int keepAliveMillSec) {
        this.initConfig(maxConnection, maxConnectionPerRoute, 0, 0, (String) null, (String) null, (String) null, keepAliveMillSec);
        this.initHttpClient();
    }

    public HttpClientPool(int maxConnection, int maxConnectionPerRoute, int httpsMaxConnection, int httpsMaxConnectionPerRoute, String httpsCertFilePath, String httpsCertKeystoreType, String httpsCertPasswd) {
        this.initConfig(maxConnection, maxConnectionPerRoute, httpsMaxConnection, httpsMaxConnectionPerRoute, httpsCertFilePath, httpsCertKeystoreType, httpsCertPasswd, 0);
        this.initHttpClient();
        this.initHttpsCertClient();
    }

    public  HttpClientPool(int maxConnection, int maxConnectionPerRoute, int httpsMaxConnection, int httpsMaxConnectionPerRoute, String httpsCertFilePath, String httpsCertKeystoreType, String httpsCertPasswd, int keepAliveMillSec) {
        this.initConfig(maxConnection, maxConnectionPerRoute, httpsMaxConnection, httpsMaxConnectionPerRoute, httpsCertFilePath, httpsCertKeystoreType, httpsCertPasswd, keepAliveMillSec);
        this.initHttpClient();
        this.initHttpsCertClient();
    }

    private void initConfig(int maxConnection, int maxConnectionPerRoute, int httpsMaxConnection, int httpsMaxConnectionPerRoute, String httpsCertFilePath, String httpsCertKeystoreType, String httpsCertPasswd, int keepAliveMillSec) {
        if (maxConnection > 0) {
            this.maxConnection = maxConnection;
        }

        log.info("[HttpClient init] Http conn pool max total: " + this.maxConnection);
        if (maxConnectionPerRoute > 0) {
            this.maxConnectionPerRoute = maxConnectionPerRoute;
        }

        log.info("[HttpClient init] Http conn pool max per route: " + this.maxConnectionPerRoute);
        if (httpsMaxConnection > 0) {
            this.httpsMaxConnection = httpsMaxConnection;
        }

        log.info("[HttpClient init] Https cert conn pool max total: " + this.httpsMaxConnection);
        if (httpsMaxConnectionPerRoute > 0) {
            this.httpsMaxConnectionPerRoute = httpsMaxConnectionPerRoute;
        }

        log.info("[HttpClient init] Https cert conn pool max per route: " + this.httpsMaxConnectionPerRoute);
        if (StringUtils.isNotBlank(httpsCertFilePath)) {
            this.httpsCertFilePath = httpsCertFilePath;
        }

        log.info("[HttpClient init] Https cert file: " + this.httpsCertFilePath);
        if (StringUtils.isNotBlank(httpsCertKeystoreType)) {
            this.httpsCertKeystoreType = httpsCertKeystoreType;
        }

        log.info("[HttpClient init] Https cert keystore type: " + this.httpsCertKeystoreType);
        if (StringUtils.isNotBlank(httpsCertPasswd)) {
            this.httpsCertPasswd = httpsCertPasswd;
        }

        log.info("[HttpClient init] Https cert password: " + this.httpsCertPasswd);
        if (keepAliveMillSec > 0) {
            this.keepAliveMillSec = keepAliveMillSec;
        }

        log.info("[HttpClient init] keep alive millisecond: " + this.keepAliveMillSec);
    }

    private void initHttpClient() {
        try {
            SSLContext sslContext = SSLContexts.custom().useTLS().build();
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, null);
            Registry socketFactoryRegistry = RegistryBuilder.create().register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
                        public void verify(String s, SSLSocket sslSocket) throws IOException {
                        }

                        public void verify(String s, X509Certificate x509Certificate) throws SSLException {
                        }

                        public void verify(String s, String[] strings, String[] strings1) throws SSLException {
                        }

                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    })).build();
            DefaultConnectionKeepAliveStrategy keepAliveStrategy = getDefaultConnectionKeepAliveStrategy();
            this.httpConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            this.httpClient = HttpClients.custom().setConnectionManager(this.httpConnManager).setKeepAliveStrategy(keepAliveStrategy).build();
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).setSoReuseAddress(true).build();
            this.httpConnManager.setDefaultSocketConfig(socketConfig);
            MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
            ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();
            this.httpConnManager.setDefaultConnectionConfig(connectionConfig);
            this.httpConnManager.setMaxTotal(this.maxConnection);
            this.httpConnManager.setDefaultMaxPerRoute(this.maxConnectionPerRoute);
            HttpClientPoolIdleMonitor.scheduledTask(new HttpClientPoolIdleMonitorTask(this.httpConnManager));
        } catch (NoSuchAlgorithmException e) {
            log.error("InitHttpClient Exception:", e);
            e.printStackTrace();
        } catch (KeyManagementException e) {
            log.error("InitHttpClient Exception:", e);
            e.printStackTrace();
        }
    }

    private void initHttpsCertClient() {
        if (!StringUtils.isBlank(this.httpsCertFilePath)) {
            try {
                KeyStore e = KeyStore.getInstance(this.httpsCertKeystoreType);
                e.load(HttpClientPool.class.getResourceAsStream(this.httpsCertFilePath), this.httpsCertPasswd.toCharArray());
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(e, this.httpsCertPasswd.toCharArray());
                SSLContext sslContext = SSLContexts.custom().useTLS().build();
                sslContext.init(kmf.getKeyManagers(), new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }}, (SecureRandom) null);
                Registry socketFactoryRegistry = RegistryBuilder.create().register("https", new SSLConnectionSocketFactory(sslContext)).build();
                DefaultConnectionKeepAliveStrategy keepAliveStrategy = getDefaultConnectionKeepAliveStrategy();
                this.httpsCertConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                this.httpsCertClient = HttpClients.custom().setConnectionManager(this.httpsCertConnManager).setKeepAliveStrategy(keepAliveStrategy).build();
                SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).setSoReuseAddress(true).build();
                this.httpsCertConnManager.setDefaultSocketConfig(socketConfig);
                MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
                ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();
                this.httpsCertConnManager.setDefaultConnectionConfig(connectionConfig);
                this.httpsCertConnManager.setMaxTotal(this.httpsMaxConnection);
                this.httpsCertConnManager.setDefaultMaxPerRoute(this.httpsMaxConnectionPerRoute);
                HttpClientPoolIdleMonitor.scheduledTask(new HttpClientPoolIdleMonitorTask(this.httpsCertConnManager));
            } catch (Exception var9) {
                log.error("InitHttpsCertClient Exception: ", var9);
            }
        }
    }

    private boolean checkInit() {
        if (this.httpClient == null) {
            log.error("Initialize httpClient failed.");
        }
        return this.httpClient != null;
    }

    private boolean checkHttpsCertInit() {
        if (this.httpsCertClient == null) {
            log.error("Initialize httpsCertClient failed.");
        }
        return this.httpsCertClient != null;
    }

    public String invokePost(String url, Map<String, Object> params, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        if (!this.checkInit()) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("url : ").append(url).append(", params : ").append(params.toString());
            log.debug("[HttpClientPool POST] Begin post, " + sb.toString());
            ArrayList<BasicNameValuePair> paramPairs = new ArrayList<BasicNameValuePair>();
            if (!params.isEmpty()) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    paramPairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }

            HttpPost httpPost1 = new HttpPost(url);
            RequestConfig requestConfig1 = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).setExpectContinueEnabled(false).build();
            httpPost1.setConfig(requestConfig1);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> statusCode : headers.entrySet()) {
                    httpPost1.setHeader(statusCode.getKey(), statusCode.getValue());
                }
            }
            try {
                httpPost1.setEntity(new UrlEncodedFormEntity(paramPairs, StringUtils.isEmpty(encode) ? Consts.UTF_8.name() : encode));
                CloseableHttpResponse e2 = null;

                String var15;
                try {
                    e2 = this.httpClient.execute(httpPost1);
                    int statusCode1 = e2.getStatusLine().getStatusCode();
                    HttpEntity entity = e2.getEntity();
                    String result = null;
                    if (entity != null) {
                        result = EntityUtils.toString(entity, Consts.UTF_8);
                    }
                    log.debug("[HttpClientPool POST] RequestUri : " + sb.toString() + ", Response status code : " + statusCode1 + ", Response content : " + result);
                    var15 = result;
                } finally {
                    if (e2 != null) {
                        e2.close();
                    }
                }
                return var15;
            } catch (Exception var20) {
                log.error("Exception", var20);
                httpPost1.abort();
                return null;
            }
        }
    }

    public String invokePost(String url, Map<String, Object> params, String encode, int connectTimeout, int soTimeout) {
        return this.invokePost(url, params, null, encode, connectTimeout, soTimeout);
    }

    public String invokePost(String url, String content, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        if (!this.checkInit()) {
            return null;
        } else if (content != null && content.length() != 0) {
            log.debug("[HttpClientPool POST] Begin post, " + "url : " + url + ", content: " + content);
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).setExpectContinueEnabled(false).build();
            httpPost.setConfig(requestConfig);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            encode = StringUtils.isEmpty(encode) ? Consts.UTF_8.name() : encode;

            try {
                StringEntity e1 = new StringEntity(content, encode);
                httpPost.setEntity(e1);
                CloseableHttpResponse response1 = null;
                String var15;
                try {
                    response1 = this.httpClient.execute(httpPost);
                    int statusCode = response1.getStatusLine().getStatusCode();
                    HttpEntity entity = response1.getEntity();
                    String result = null;
                    if (entity != null) {
                        result = EntityUtils.toString(entity, Consts.UTF_8);
                    }
                    log.debug("[HttpClientPool POST] RequestUri : " + url + " content:" + content + ", Response status code : " + statusCode + ", Response content : " + result);
                    var15 = result;
                } finally {
                    if (response1 != null) {
                        response1.close();
                    }
                }
                return var15;
            } catch (Exception var20) {
                httpPost.abort();
                log.error("Exception", var20);
                return null;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String invokePost(String url, String content, String encode, int connectTimeout, int soTimeout) {
        return this.invokePost(url, content, null, encode, connectTimeout, soTimeout);
    }

    private DefaultConnectionKeepAliveStrategy getDefaultConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1L) {
                    keepAlive = (long) HttpClientPool.this.keepAliveMillSec;
                }
                return keepAlive;
            }
        };
    }

    public String invokePostWithHttpsCert(String url, String content, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        if (!this.checkHttpsCertInit()) {
            return null;
        } else if (content != null && content.length() != 0) {
            log.debug("[HttpClientPool POST] Begin post, " + "url : " + url + ", content: " + content);
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).setExpectContinueEnabled(false).build();
            httpPost.setConfig(requestConfig);
            if (headers != null && !headers.isEmpty()) {

                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            encode = StringUtils.isEmpty(encode) ? Consts.UTF_8.name() : encode;

            try {
                InputStreamEntity e1 = new InputStreamEntity(new ByteArrayInputStream(content.getBytes(encode)));
                httpPost.setEntity(e1);
                CloseableHttpResponse response1 = null;

                String var15;
                try {
                    response1 = this.httpsCertClient.execute(httpPost);
                    int statusCode = response1.getStatusLine().getStatusCode();
                    HttpEntity entity = response1.getEntity();
                    String result = null;
                    if (entity != null) {
                        result = EntityUtils.toString(entity, Consts.UTF_8);
                    }

                    log.debug("[HttpClientPool POST] RequestUri : " + url + ", Response status code : " + statusCode + ", Response content : " + result);
                    var15 = result;
                } finally {
                    if (response1 != null) {
                        response1.close();
                    }

                }

                return var15;
            } catch (Exception var20) {
                httpPost.abort();
                log.error("Exception", var20);
                return null;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String invokeGet(String url, Map<String, String> params, int connectTimeout, int socketTimeout) {
        return this.invokeGet(url, params, null, connectTimeout, socketTimeout);
    }

    public String invokeGet(String url, Map<String, String> params, Map<String, String> headers, int connectTimeout, int socketTimeout) {
        if (!this.checkInit()) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            int i = 0;
            if (params != null) {
                for (Iterator<Map.Entry<String, String>> httpGet = params.entrySet().iterator(); httpGet.hasNext(); ++i) {
                    Map.Entry<String, String> config = httpGet.next();
                    if (i == 0 && !url.contains("?")) {
                        sb.append("?");
                    } else {
                        sb.append("&");
                    }
                    sb.append(config.getKey());
                    sb.append("=");
                    String e = config.getValue();

                    try {
                        sb.append(URLEncoder.encode(e, "UTF-8"));
                    } catch (UnsupportedEncodingException var20) {
                        log.warn("encode http get params error, value is " + e, var20);
                        sb.append(URLEncoder.encode(e));
                    }
                }
            }
            log.debug("[HttpClientPool Get] begin invoke:" + sb.toString());
            HttpGet var23 = new HttpGet(sb.toString());
            RequestConfig var24 = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
            var23.setConfig(var24);
            if (headers != null && !headers.isEmpty()) {
                Iterator<Map.Entry<String, String>> var25 = headers.entrySet().iterator();

                while (var25.hasNext()) {
                    Map.Entry<String, String> statusCode = var25.next();
                    var23.setHeader(statusCode.getKey(), statusCode.getValue());
                }
            }

            try {
                CloseableHttpResponse var26 = null;

                String var15;
                try {
                    var26 = this.httpClient.execute(var23);
                    int var27 = var26.getStatusLine().getStatusCode();
                    HttpEntity entity = var26.getEntity();
                    String result = null;
                    if (entity != null) {
                        result = EntityUtils.toString(entity, Consts.UTF_8);
                    }

                    log.debug("[HttpClientPool Get] RequestUri : " + sb.toString() + ", Response status code : " + var27 + ", Response content : " + result);
                    var15 = result;
                } finally {
                    if (var26 != null) {
                        var26.close();
                    }

                }

                return var15;
            } catch (Exception var22) {
                var23.abort();
                log.error(String.format("[HttpClientPool Get]invoke get error, url:%s", new Object[]{sb.toString()}), var22);
                return null;
            }
        }
    }

    public String invotePostForMultiPart(File file, String url, Map<String, String> params, int connectTimeout, int soTimeout) {
        return this.invotePostForMultiPart(file, url, params, null, connectTimeout, soTimeout);
    }

    public String invotePostForMultiPart(File file, String url, Map<String, String> params, Map<String, String> headers, int connectTimeout, int soTimeout) {
        if (!this.checkInit()) {
            return null;
        } else {
            log.debug("[HttpClientPool POST] Begin post, " + "url : " + url + ", params : " + params.toString() + ", filename=" + file.getName());
            String responseContent = null;
            HttpPost httpPost = new HttpPost(url);

            try {
                RequestConfig e = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).setExpectContinueEnabled(false).build();
                httpPost.setConfig(e);
                if (headers != null && !headers.isEmpty()) {

                    for (Object o : headers.entrySet()) {
                        Map.Entry fileBody = (Map.Entry) o;
                        httpPost.setHeader((String) fileBody.getKey(), (String) fileBody.getValue());
                    }
                }

                MultipartEntity reqEntity1 = new MultipartEntity();
                FileBody fileBody1 = new FileBody(file);
                reqEntity1.addPart("pic", fileBody1);

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    StringBody entity = new StringBody(entry.getValue(), Charset.forName("UTF-8"));
                    reqEntity1.addPart(entry.getKey(), entity);
                }

                httpPost.setEntity(reqEntity1);
                CloseableHttpResponse response1 = null;

                try {
                    response1 = this.httpClient.execute(httpPost);
                    int statusCode1 = response1.getStatusLine().getStatusCode();
                    HttpEntity entity1 = response1.getEntity();
                    if (null != entity1) {
                        responseContent = EntityUtils.toString(entity1, Consts.UTF_8);
                    }

                    log.debug("[HttpClientPool POST] requestURI : " + httpPost.getURI() + ", Response status code : " + statusCode1 + ", responseContent: " + responseContent);
                } finally {
                    if (response1 != null) {
                        response1.close();
                    }

                }
            } catch (Exception var20) {
                httpPost.abort();
                log.error("IOException", var20);
            }

            return responseContent;
        }
    }
}
