package com.wentjiang.crawler.http;

import com.wentjiang.crawler.util.PropertiesUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Created by jiangwentao on 12/5/2016 3:52 PM.
 */
@Slf4j
public class HttpClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);
    private static String configFileName = "/httpClientPool.properties";
    @Getter
    private static HttpClientPool httpClientPool = null;
    private static int POOL_MAX_TOTAL = 100;
    private static int POOL_MAX_PER_ROUTE = 50;
    private static int HTTPS_CERT_POOL_MAX_TOTAL = 100;
    private static int HTTPS_CERT_POOL_MAX_PER_ROUTE = 50;
    private static String HTTPS_CERT_FILE = null;
    private static String HTTPS_CERT_KEYSTORE_TYPE = "jks";
    private static String HTTPS_CERT_PASSWORD = null;
    private static int KEEP_ALIVE_MILLIS = 30000;

    private static int CONNECT_TIMEOUT = 10000;
    private static int SOCKET_TIMEOUT = 10000;
    public HttpClientFactory() {
    }

    private static void initConfig() {
        PropertiesUtil propertiesUtil = PropertiesUtil.newInstance(configFileName);
        String POOL_MAX_TOTAL_STR = propertiesUtil.getValue("POOL_MAX_TOTAL");
        if(StringUtils.isNotBlank(POOL_MAX_TOTAL_STR)) {
            POOL_MAX_TOTAL = Integer.valueOf(POOL_MAX_TOTAL_STR).intValue();
        }

        logger.info("[HttpClient init] Http conn pool max total: " + POOL_MAX_TOTAL);
        String POOL_MAX_PER_ROUTE_STR = propertiesUtil.getValue("POOL_MAX_PER_ROUTE");
        if(StringUtils.isNotBlank(POOL_MAX_PER_ROUTE_STR)) {
            POOL_MAX_PER_ROUTE = Integer.valueOf(POOL_MAX_PER_ROUTE_STR).intValue();
        }

        logger.info("[HttpClient init] Http conn pool max per route: " + POOL_MAX_PER_ROUTE);
        String HTTPS_CERT_POOL_MAX_TOTAL_STR = propertiesUtil.getValue("HTTPS_CERT_POOL_MAX_TOTAL");
        if(StringUtils.isNotBlank(HTTPS_CERT_POOL_MAX_TOTAL_STR)) {
            HTTPS_CERT_POOL_MAX_TOTAL = Integer.valueOf(HTTPS_CERT_POOL_MAX_TOTAL_STR).intValue();
        }

        logger.info("[HttpClient init] Https cert conn pool max total: " + HTTPS_CERT_POOL_MAX_TOTAL);
        String HTTPS_CERT_POOL_MAX_PER_ROUTE_STR = propertiesUtil.getValue("HTTPS_CERT_POOL_MAX_PER_ROUTE");
        if(StringUtils.isNotBlank(HTTPS_CERT_POOL_MAX_PER_ROUTE_STR)) {
            HTTPS_CERT_POOL_MAX_PER_ROUTE = Integer.valueOf(HTTPS_CERT_POOL_MAX_PER_ROUTE_STR).intValue();
        }

        logger.info("[HttpClient init] Https cert conn pool max per route: " + HTTPS_CERT_POOL_MAX_PER_ROUTE);
        String HTTPS_CERT_FILE_STR = propertiesUtil.getValue("HTTPS_CERT_FILE");
        if(StringUtils.isNotBlank(HTTPS_CERT_FILE_STR)) {
            HTTPS_CERT_FILE = HTTPS_CERT_FILE_STR;
        }

        logger.info("[HttpClient init] Https cert file: " + HTTPS_CERT_FILE);
        String HTTPS_CERT_KEYSTORE_TYPE_STR = propertiesUtil.getValue("HTTPS_CERT_KEYSTORE_TYPE");
        if(StringUtils.isNotBlank(HTTPS_CERT_KEYSTORE_TYPE_STR)) {
            HTTPS_CERT_KEYSTORE_TYPE = HTTPS_CERT_KEYSTORE_TYPE_STR;
        }

        logger.info("[HttpClient init] Https cert keystore type: " + HTTPS_CERT_KEYSTORE_TYPE);
        String HTTPS_CERT_PASSWORD_STR = propertiesUtil.getValue("HTTPS_CERT_PASSWORD");
        if(StringUtils.isNotBlank(HTTPS_CERT_PASSWORD_STR)) {
            HTTPS_CERT_PASSWORD = HTTPS_CERT_PASSWORD_STR;
        }

        logger.info("[HttpClient init] Https cert password: " + HTTPS_CERT_PASSWORD);
        String KEEP_ALIVE_STR = propertiesUtil.getValue("KEEP_ALIVE_MILLIS");
        if(StringUtils.isNotBlank(KEEP_ALIVE_STR)) {
            KEEP_ALIVE_MILLIS = Integer.valueOf(KEEP_ALIVE_STR).intValue();
        }

        logger.info("[HttpClient init] Keep alive millisecond: " + KEEP_ALIVE_MILLIS);
    }

    private static void initHttpClientPool() {
        if(StringUtils.isBlank(HTTPS_CERT_FILE)) {
            httpClientPool = new HttpClientPool(POOL_MAX_TOTAL, POOL_MAX_PER_ROUTE, KEEP_ALIVE_MILLIS);
        } else {
            httpClientPool = new HttpClientPool(POOL_MAX_TOTAL, POOL_MAX_PER_ROUTE, HTTPS_CERT_POOL_MAX_TOTAL, HTTPS_CERT_POOL_MAX_PER_ROUTE, HTTPS_CERT_FILE, HTTPS_CERT_KEYSTORE_TYPE, HTTPS_CERT_PASSWORD, KEEP_ALIVE_MILLIS);
        }

    }

    private static boolean checkInit() {
        if(httpClientPool == null) {
            logger.error("Initialize httpClientPool failed.");
        }

        return httpClientPool != null;
    }

    public static String invokePost(String url, Map<String, Object> params, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokePost(url, params, encode, connectTimeout, soTimeout);
    }

    public static String invokePost(String url, Map<String, Object> params, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokePost(url, params, headers, encode, connectTimeout, soTimeout);
    }

    public static String invokePost(String url, String content, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokePost(url, content, encode, connectTimeout, soTimeout);
    }

    public static String invokePost(String url, String content, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokePost(url, content, headers, encode, connectTimeout, soTimeout);
    }

    public static String invokePostWithHttpsCert(String url, String content, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokePostWithHttpsCert(url, content, headers, encode, connectTimeout, soTimeout);
    }
    public static String invokeGet(String url){
        return invokeGet(url,null);
    }
    public static String invokeGet(String url,Map<String, String> params){
        return invokeGet(url,params,CONNECT_TIMEOUT,SOCKET_TIMEOUT);
    }
    public static String invokeGet(String url, Map<String, String> params, int connectTimeout, int socketTimeout) {
        return !checkInit()?null:httpClientPool.invokeGet(url, params, connectTimeout, socketTimeout);
    }

    public static String invokeGet(String url, Map<String, String> params, Map<String, String> headers, String encode, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invokeGet(url, params, headers, connectTimeout, soTimeout);
    }

    public static String invotePostForMultiPart(File file, String url, Map<String, String> params, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invotePostForMultiPart(file, url, params, connectTimeout, soTimeout);
    }

    public static String invotePostForMultiPart(File file, String url, Map<String, String> params, Map<String, String> headers, int connectTimeout, int soTimeout) {
        return !checkInit()?null:httpClientPool.invotePostForMultiPart(file, url, params, headers, connectTimeout, soTimeout);
    }

    public static HttpClientPool newPoolInstance(int maxConnection, int maxConnectionPerRoute) {
        return new HttpClientPool(maxConnection, maxConnectionPerRoute, 0, 0, (String)null, (String)null, (String)null, 0);
    }

    public static HttpClientPool newPoolInstance(int maxConnection, int maxConnectionPerRoute, int keepAliveMillSec) {
        return new HttpClientPool(maxConnection, maxConnectionPerRoute, 0, 0, (String)null, (String)null, (String)null, keepAliveMillSec);
    }

    public static HttpClientPool newHttpsCertPoolInstance(int maxConnection, int maxConnectionPerRoute, int httpsMaxConnection, int httpsMaxConnectionPerRoute, String httpsCertFilePath, String httpsCertKeystoreType, String httpsCertPasswd) {
        return new HttpClientPool(maxConnection, maxConnectionPerRoute, httpsMaxConnection, httpsMaxConnectionPerRoute, httpsCertFilePath, httpsCertKeystoreType, httpsCertPasswd, 0);
    }

    public static HttpClientPool newHttpsCertPoolInstance(int maxConnection, int maxConnectionPerRoute, int httpsMaxConnection, int httpsMaxConnectionPerRoute, String httpsCertFilePath, String httpsCertKeystoreType, String httpsCertPasswd, int keepAliveMillSec) {
        return new HttpClientPool(maxConnection, maxConnectionPerRoute, httpsMaxConnection, httpsMaxConnectionPerRoute, httpsCertFilePath, httpsCertKeystoreType, httpsCertPasswd, keepAliveMillSec);
    }

    static {
        initConfig();
        initHttpClientPool();
    }
}
