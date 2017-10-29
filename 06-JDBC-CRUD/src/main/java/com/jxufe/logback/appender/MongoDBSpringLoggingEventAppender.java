package com.jxufe.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.jxufe.logback.MongoDBSpringAppenderBase;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import java.sql.Timestamp;

/**
 * MongoDBSpringLoggingEventAppender
 *
 * @author liuburu
 * @create 2017/08/13
 **/

public class MongoDBSpringLoggingEventAppender extends MongoDBSpringAppenderBase<ILoggingEvent> {
    private boolean includeCallerData;

    public MongoDBSpringLoggingEventAppender() {
        System.out.println("MongoDBSpringLoggingEventAppender构造函数");
    }

    protected BasicDBObject toMongoDocument(ILoggingEvent event) {
        BasicDBObject logEntry = new BasicDBObject();
        logEntry.append("message", event.getFormattedMessage());
        logEntry.append("logger", event.getLoggerName());
        logEntry.append("thread", event.getThreadName());
        logEntry.append("timestamp", new Timestamp(event.getTimeStamp()));
        logEntry.append("level", event.getLevel().toString());
        if(event.getMDCPropertyMap() != null && !event.getMDCPropertyMap().isEmpty()) {
            logEntry.append("mdc", event.getMDCPropertyMap());
        }

        if(this.includeCallerData) {
            logEntry.append("callerData", this.toDocument(event.getCallerData()));
        }

        if(event.getArgumentArray() != null && event.getArgumentArray().length > 0) {
            logEntry.append("arguments", event.getArgumentArray());
        }

        this.appendThrowableIfAvailable(logEntry, event);
        return logEntry;
    }

    private BasicDBList toDocument(StackTraceElement[] callerData) {
        BasicDBList dbList = new BasicDBList();
        StackTraceElement[] arr$ = callerData;
        int len$ = callerData.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StackTraceElement ste = arr$[i$];
            dbList.add((new BasicDBObject()).append("file", ste.getFileName()).append("class", ste.getClassName()).append("method", ste.getMethodName()).append("line", Integer.valueOf(ste.getLineNumber())).append("native", Boolean.valueOf(ste.isNativeMethod())));
        }

        return dbList;
    }

    private void appendThrowableIfAvailable(BasicDBObject doc, ILoggingEvent event) {
        if(event.getThrowableProxy() != null) {
            BasicDBObject val = this.toMongoDocument(event.getThrowableProxy());
            doc.append("throwable", val);
        }

    }

    private BasicDBObject toMongoDocument(IThrowableProxy throwable) {
        BasicDBObject throwableDoc = new BasicDBObject();
        throwableDoc.append("class", throwable.getClassName());
        throwableDoc.append("message", throwable.getMessage());
        throwableDoc.append("stackTrace", this.toSteArray(throwable));
        if(throwable.getCause() != null) {
            throwableDoc.append("cause", this.toMongoDocument(throwable.getCause()));
        }

        return throwableDoc;
    }

    private String[] toSteArray(IThrowableProxy throwableProxy) {
        StackTraceElementProxy[] elementProxies = throwableProxy.getStackTraceElementProxyArray();
        int totalFrames = elementProxies.length - throwableProxy.getCommonFrames();
        String[] stackTraceElements = new String[totalFrames];

        for(int i = 0; i < totalFrames; ++i) {
            stackTraceElements[i] = elementProxies[i].getStackTraceElement().toString();
        }

        return stackTraceElements;
    }

    public void setIncludeCallerData(boolean includeCallerData) {
        this.includeCallerData = includeCallerData;
    }
}
