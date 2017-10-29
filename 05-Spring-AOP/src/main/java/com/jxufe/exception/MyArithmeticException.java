package com.jxufe.exception;

/**
 * Created by liuburu on 2017/5/23.
 */
public class MyArithmeticException extends RuntimeException {

    public MyArithmeticException() {
        super();
    }

    public MyArithmeticException(String message) {
        super(message);
    }
}
