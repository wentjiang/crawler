package com.wentjiang.crawler.http.instant;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangwentao on 12/5/2016 4:21 PM.
 */
public class HttpClientPoolIdleMonitor {
    private static ScheduledExecutorService execs = null;
    private static LinkedBlockingQueue<HttpClientPoolIdleMonitorTask> taskQueue = new LinkedBlockingQueue();

    public HttpClientPoolIdleMonitor() {
    }

    public static void init() {
        execs = Executors.newScheduledThreadPool(5);

        while(!taskQueue.isEmpty()) {
            scheduledTask(taskQueue.poll());
        }

    }

    public static void shutdown() {
        execs.shutdown();
    }

    public static void scheduledTask(HttpClientPoolIdleMonitorTask task) {
        if(execs != null && !execs.isShutdown() && !execs.isTerminated()) {
            execs.scheduleAtFixedRate(task, 5L, 5000L, TimeUnit.MILLISECONDS);
        } else {
            taskQueue.offer(task);
        }

    }
}
