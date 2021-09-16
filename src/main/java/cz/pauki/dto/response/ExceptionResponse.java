package cz.pauki.dto.response;

import cz.pauki.ennumerate.ErrorCodes;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * Object for all server exception
 */
public class ExceptionResponse {

    private final ErrorCodes errorCode;
    private final String errorMessage;

    public ExceptionResponse(@NotNull ErrorCodes errorCode, @NotNull String errorMessage) {
        Assert.notNull(errorCode, "errorCode cannot be null");
        Assert.notNull(errorMessage, "errorMessage cannot be null");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
