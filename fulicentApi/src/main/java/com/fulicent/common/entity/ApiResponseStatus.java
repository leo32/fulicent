package com.fulicent.common.entity;

public enum ApiResponseStatus {
	SYSTEM_HEALTH("200200", "System is healthy"),
    SYSTEM_INTERNAL_ERROR("500500", "Internal Error"),
    SYSTEM_INVALID_PARAMETER("400501", "Invalid parameter"),
    SYSTEM_CONNECTION_ERROR("500502", "Connection Error"),
    RESOURCE_NOT_FOUND("400400", "%s not found"),
    RELATED_RESOURCE_FOUND("400401", "Related resource exist need to be cleaned first"),
    RESOURCE_DELETE_FAILED("400402", "%s delete failed"), RESOURCE_CREATE_FAILED("400403", "%s create failed"),
    RESOURCE_UPDATE_FAILED("400404", "%s update failed"), RESOURCE_FOUND("200201", "%s found"),
    RESOURCE_DELETE_SUCCESS("200202", "%s delete successfully"),
    RESOURCE_CREATE_SUCCESS("200203", "%s create successfully"),
    RESOURCE_UPDATE_SUCCESS("200204", "%s update successfully");
    
    private String appCode;
    private String message;

    public String getAppCode() {
        return this.appCode;
    }

    public String getMessage() {
        return this.message;
    }

    public static ApiResponseStatus of(String appCode) {
        ApiResponseStatus[] responseCodes = values();
        for (ApiResponseStatus responseCode : responseCodes) {
            if (responseCode.getAppCode().equals(appCode)) {
                return responseCode;
            }
        }

        throw new IllegalArgumentException("No matching app code for [" + appCode + "]");
    }

    ApiResponseStatus(String appCode, String message) {
        this.appCode = appCode;
        this.message = message;
    }
}
