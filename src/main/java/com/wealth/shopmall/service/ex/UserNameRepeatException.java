package com.wealth.shopmall.service.ex;

/**
 * 用户名重复异常
 */
public class UserNameRepeatException extends ServiceException{
    public UserNameRepeatException() {
        super();
    }

    public UserNameRepeatException(String message) {
        super(message);
    }

    public UserNameRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameRepeatException(Throwable cause) {
        super(cause);
    }

    protected UserNameRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
