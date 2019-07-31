package com.frame.exception;

/**
 * Created by wuming on 2019/7/30.
 */
public class NullIdException extends Exception {
    private static final long serialVersionUID = 1L;

    public NullIdException(String message) {
        super(message);
    }

    public NullIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
