package com.api.neki.controller;

import com.api.neki.dto.UserReqDTO;
import com.api.neki.dto.UserRespDTO;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.services.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping
    public ResponseEntity<UserRespDTO> createUser(@RequestBody UserReqDTO usuarioRequest){

        if(usuarioRequest.getNomeUsuario() == null || usuarioRequest.getNomeUsuario().length() < 1){
            throw new NekiException("Nome do usuario não pode estar vazio.");
        }

        if(usuarioRequest.getEmail() == null || usuarioRequest.getEmail().length() < 1){
            throw new  NekiException("E-mail não pode estar vazio.");
        }

        if(usuarioRequest.getSenha() == null || usuarioRequest.getSenha().length() < 1){
            throw new  NekiException("Senha não pode estar vazio.");
        } else if(usuarioRequest.getSenha().length() > 20){
            throw new  NekiException("Limite de caracteres excedido, MAX:20");
        }

        return ResponseEntity.status(201).body(cadastroService.createUser(usuarioRequest));
    }
}
