package com.api.neki.dto;

import com.api.neki.entities.ETipoPerfil;

public class UserRespDTO extends UserDTO {

    private Long id;
    private ETipoPerfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ETipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }
}
