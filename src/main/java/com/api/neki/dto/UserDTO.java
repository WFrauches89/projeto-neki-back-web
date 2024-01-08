package com.api.neki.dto;

public abstract class UserDTO {

    private String nameUser;
    private String email;

    public String getNomeUsuario() {
        return nameUser;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nameUser = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
