package org.mybatis.example.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuburu
 * @create 2017/08/10
 **/
public class TestLogback {
    private static Logger log = LoggerFactory.getLogger(TestLogback.class);
    public static void main(String[] args) {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
    }
}
