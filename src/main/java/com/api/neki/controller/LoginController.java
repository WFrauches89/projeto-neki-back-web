package com.api.neki.controller;

import com.api.neki.dto.LoginReqDTO;
import com.api.neki.dto.LoginRespDTO;
import com.api.neki.services.LoginService;
import com.api.neki.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginRespDTO> logar(@RequestBody LoginReqDTO usuariologinRequest){
        LoginRespDTO usuarioLogado = loginService.logar(
                usuariologinRequest.getEmail(),
                usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(usuarioLogado);
    }
}
