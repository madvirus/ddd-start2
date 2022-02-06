package com.myshop.lock;

public class LockingFailException extends LockException {
    public LockingFailException() {
    }

    public LockingFailException(Exception cause) {
        super(cause);
    }
}
