package com.wentjiang.crawler.http.instant;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.HttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiangwentao on 12/5/2016 4:20 PM.
 */
@Slf4j
public class HttpClientPoolIdleMonitorTask implements Runnable {
    private final HttpClientConnectionManager connMgr;

    public HttpClientPoolIdleMonitorTask(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    public void run() {
        synchronized (this.connMgr) {
            log.debug("Close expired and idle connections...");
            this.connMgr.closeExpiredConnections();
            this.connMgr.closeIdleConnections(60L, TimeUnit.SECONDS);
            log.debug("Close expired and idle connections finished.");
        }
    }
}
