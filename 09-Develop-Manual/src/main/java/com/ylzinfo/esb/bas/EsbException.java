package com.ylzinfo.esb.bas;

import java.util.ArrayList;
import java.util.List;

/**
 * ESB异常错误类
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2011-12-15 Created.
 * Version: 3.0
 */

public class EsbException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8288841658890821643L;
	private List exceptions = new ArrayList();
    private String messageKey = null;
    private Object[] messageArgs = null;
	private int errorCode;
    public EsbException() {
        super();
    }

	public EsbException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}
    
    public EsbException(String key, Object[] args) {
        super();
        this.messageKey = key;
        this.messageArgs = args;
    }

    public EsbException(String key) {
        super();
        this.messageKey = key;
        this.messageArgs = null;
    }

    public EsbException(Throwable rootCause, String key, Object[] args) {
        super(rootCause);
        this.messageKey = key;
        this.messageArgs = args;
    }

    public EsbException(Throwable rootCause) {
        super(rootCause);
    }

    public List getExceptions() {
        return exceptions;
    }

    public void addException(EsbException ex) {
        exceptions.add(ex);
    }

    public void setMessageKey(String key) {
        this.messageKey = key;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageArgs(Object[] args) {
        this.messageArgs = args;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public String getMessage() {
        String myMessage = super.getMessage();
        if (myMessage == null) myMessage = "LTCM Exception - Error Key : " + this.messageKey;
        return myMessage;
    }
	public int getErrorCode() {
		return this.errorCode;
	}
    public String[] getMessages() {
        Throwable[] throwables = this.getThrowables();
        String[] msgs = new String[throwables.length];
        for (int i = 0; i < throwables.length; i++) {
            msgs[i] = throwables[i].getMessage();
        }
        return msgs;
    }

    public Throwable[] getThrowables() {
        List list = new ArrayList();
        Throwable throwable = this;
        while (throwable != null) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return (Throwable[]) list.toArray(new Throwable[list.size()]);
    }
}