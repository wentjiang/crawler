package com.wentjiang.crawler.common;

import java.util.LinkedList;

/**
 * Created by jiangwentao on 12/5/2016 2:12 PM.
 */
public class Queue {
    /**
     * 定义一个队列，使用LinkedList实现
     */
    private LinkedList<String> queue = new LinkedList<>(); // 入队列

    /**
     * 将t加入到队列中
     */
    public void enQueue(String t) {
        queue.addLast(t);
    }

    /**
     * 移除队列中的第一项并将其返回
     */
    public String deQueue() {
        return queue.removeFirst();
    }

    /**
     * 返回队列是否为空
     */
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    /**
     * 判断并返回队列是否包含t
     */
    public boolean contians(Object t) {
        return queue.contains(t);
    }

    /**
     * 判断并返回队列是否为空
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
