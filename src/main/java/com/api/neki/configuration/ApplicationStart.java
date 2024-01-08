package com.api.neki.configuration;

import javax.annotation.PostConstruct;

import com.api.neki.dto.UserReqDTO;
import com.api.neki.repository.UserRepository;
import com.api.neki.services.CadastroService;
import com.api.neki.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStart {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private CadastroService cadastroService;

    @PostConstruct
    public void setupInitialData() {
        if (usuarioRepository.count() == 0) {
            cadastroService.createUser(userMaster());
        }
    }

    private UserReqDTO userMaster() {
        UserReqDTO masterUser = new UserReqDTO();
        masterUser.setNomeUsuario("Wanderson");
        masterUser.setEmail("admin@admin.com");
        masterUser.setSenha("admin");

        return masterUser;

    }

}