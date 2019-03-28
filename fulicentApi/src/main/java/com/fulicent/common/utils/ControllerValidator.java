package com.fulicent.common.utils;

import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.exception.ApiException;
import org.apache.commons.lang3.StringUtils;

import static com.fulicent.common.entity.ApiResponseStatus.SYSTEM_INVALID_PARAMETER;

public class ControllerValidator {

    public static void validateNumericParameter(String parameter, String message) {
        if (StringUtils.isEmpty(parameter) || !isNumeric(parameter)) {
            throw new ApiException(SYSTEM_INVALID_PARAMETER, message);
        }
    }

    public static void validateEmptyParameter(String parameter, String message) {
        if (StringUtils.isBlank(parameter)) {
            throw new ApiException(SYSTEM_INVALID_PARAMETER, message);
        }
    }

    public static void validateNullResource(Object entity, ApiResponseStatus status) {
        if (entity == null) {
            throw new ApiException(status, "Resource doesn't exist or no permission");
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
