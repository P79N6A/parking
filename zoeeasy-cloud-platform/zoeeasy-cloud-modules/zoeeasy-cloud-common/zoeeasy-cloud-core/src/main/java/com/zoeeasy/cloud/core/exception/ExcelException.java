package com.zoeeasy.cloud.core.exception;

public class ExcelException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExcelException(String code) {
        super(code);
    }

    public ExcelException(String code, Object... arguments) {
        super(code);
    }

    public ExcelException(String code, Throwable cause) {
        super(code, cause);
    }
}
