package com.transport.workitem_ms.exception;

public class WorkitemNotFoundException extends RuntimeException{
    public WorkitemNotFoundException(String message) {
        super(message);
    }
}
