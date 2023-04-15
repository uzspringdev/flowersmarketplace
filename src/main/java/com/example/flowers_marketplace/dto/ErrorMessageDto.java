package com.example.flowers_marketplace.dto;


public class ErrorMessageDto {
    private String errorMessage;
    private Integer errorCode;

    public ErrorMessageDto() {
    }

    public ErrorMessageDto(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
