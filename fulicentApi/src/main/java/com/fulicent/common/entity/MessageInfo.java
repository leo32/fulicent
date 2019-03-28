package com.fulicent.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageInfo {
    @JsonProperty("ResponseStatus")
    private String status;

    @JsonProperty("Hint")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hint;

    public MessageInfo(ApiResponseStatus responseStatus) {
        this(responseStatus, responseStatus.getMessage());
    }

    public MessageInfo(ApiResponseStatus responseStatus, String customMessage) {
        this.status = responseStatus.getAppCode();
        this.hint = customMessage;
    }
}
