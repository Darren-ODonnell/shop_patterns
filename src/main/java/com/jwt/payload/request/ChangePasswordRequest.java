package com.jwt.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ChangePasswordRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 1, max = 40)
    private String oldPassword;

    @NotBlank
    @Size(min = 1, max = 40)
    private String newPassword;

    @NotBlank
    @Size(min = 1, max = 40)
    private String passwordConfirm;

    public String getUsername() {       return username;    }
    public void setUsername(String username) {        this.username          = username;    }
    public String getOldPassword() {        return oldPassword;    }
    public void setOldPassword(String oldPassword) {        this.oldPassword = oldPassword;    }
    public String getNewPassword() {        return newPassword;    }
    public void setNewPassword(String newPassword) {        this.newPassword = newPassword;    }
    public String getPasswordConfirm() {        return passwordConfirm;    }
    public void setPasswordConfirm(String passwordConfirm) {        this.passwordConfirm = passwordConfirm;    }

}