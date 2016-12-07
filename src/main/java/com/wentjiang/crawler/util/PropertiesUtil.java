package com.wentjiang.crawler.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jiangwentao on 12/5/2016 3:32 PM.
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private Properties properties = new Properties();
    private String fileName;

    public static PropertiesUtil newInstance(String fileName) {
        return new PropertiesUtil(fileName);
    }

    public PropertiesUtil(String fileName) {
        this.fileName = fileName;
        reload();
    }

    public String getValue(String key) {
        String value = properties.getProperty(key);
        return value == null ? value : value.trim();
    }

    public String getValue(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        return value == null ? value : value.trim();
    }

    public void reload() {
        if (StringUtils.isNotEmpty(fileName)) {
            if (!fileName.startsWith("/")) {
                fileName = "/" + fileName;
            }
            InputStream e = PropertiesUtil.class.getResourceAsStream(this.fileName);
            try {
                this.properties.load(e);
            } catch (IOException e1) {
                logger.error("Properties file load failed, file name is ：" + this.fileName);
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
