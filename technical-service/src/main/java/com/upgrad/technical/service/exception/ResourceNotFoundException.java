package com.upgrad.technical.service.exception;


import java.io.PrintStream;
import java.io.PrintWriter;

public class ResourceNotFoundException extends Exception{
    private String code;
    private String errorMessage;

    public ResourceNotFoundException(String code,String message){
        this.code = code;
        this.errorMessage = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
