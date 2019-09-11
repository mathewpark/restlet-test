package com.example.server;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jHttpMethodFilter extends Filter {

    @Override
    public int decide(LoggingEvent event) {
        try {
            if ((event.getMDC(C.MDC_LOG_LEVEL)) == null) {
                return NEUTRAL;
            } else {
                if (event.getLevel().isGreaterOrEqual(
                        (Level) event.getMDC(C.MDC_LOG_LEVEL))) {
                    return NEUTRAL;
                } else {
                    return DENY;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Log4jHttpMethodFilter.class).error(e);
            return NEUTRAL;
        }
    }

}
