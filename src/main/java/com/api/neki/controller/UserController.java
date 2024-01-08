package com.api.neki.controller;

import java.util.List;

import com.api.neki.dto.LoginReqDTO;
import com.api.neki.dto.UserReqDTO;
import com.api.neki.dto.UserRespDTO;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService usuarioService;


    @GetMapping
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<List<UserRespDTO>> obterTodos(){

        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
	@PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<UserRespDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<UserRespDTO> obterPorEmail(@PathVariable String email){
        return ResponseEntity.ok(usuarioService.obterPorEmail(email));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRespDTO> atualizar(@PathVariable Long id, @RequestBody UserReqDTO usuarioRequest){
        if(usuarioRequest.getNomeUsuario() == null || usuarioRequest.getNomeUsuario().length() < 1){
            throw new  NekiException("Nome do usuario não pode estar vazio.");
        }

        if(usuarioRequest.getEmail() == null || usuarioRequest.getEmail().length() < 1){
            throw new  NekiException("E-mail não pode estar vazio.");
        }

        if(usuarioRequest.getSenha() != null && usuarioRequest.getSenha().length() > 20){
            throw new  NekiException("Limite de caracteres excedido, MAX:20");
        }

        return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("alterarsenha/{id}")
    public ResponseEntity<UserRespDTO> editarSenha(@PathVariable Long id, @RequestBody String novaSenha){
        if(novaSenha == null || novaSenha.length() < 1){
            throw new  NekiException("Senha não pode estar vazio.");
        } else if(novaSenha.length() > 20){
            throw new  NekiException("Limite de caracteres excedido, MAX:20");
        }

        return ResponseEntity.status(200).body(usuarioService.editarSenha(id, novaSenha));
    }

    @PostMapping("/verificarsenhaatual")
    public ResponseEntity<Boolean> verificarSenhaAtual(@RequestBody LoginReqDTO usuariologinRequest){
        Boolean verificado = usuarioService.verificarSenhaAtual(usuariologinRequest.getEmail(), usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(verificado);
    }
}