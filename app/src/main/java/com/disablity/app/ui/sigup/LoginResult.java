package com.disablity.app.ui.sigup;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    @Nullable
    private String message;

    @Nullable
    public String getMessage()
    {
        return message;
    }

    public void setMessage(@Nullable String message)
    {
        this.message = message;
    }

    public LoginResult(@Nullable Integer error) {
        this.error = error;
        message="";
    }

    public LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    public LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}
