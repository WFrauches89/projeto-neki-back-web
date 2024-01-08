package com.api.neki.dto;

public class LoginRespDTO {

    private String token;
    private UserRespDTO user;

    public LoginRespDTO(String token, UserRespDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserRespDTO getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(UserRespDTO user) {
        this.user = user;
   }
}
