package com.api.neki.services;

import java.util.Collections;

import com.api.neki.dto.UserReqDTO;
import com.api.neki.dto.UserRespDTO;
import com.api.neki.entities.User;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.repository.UserRepository;
import com.api.neki.security.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String BEARER = "Bearer ";

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public List<UserRespDTO> obterTodos(){
        List<User> usuarios = usuarioRepository.findAll();

        return usuarios
                .stream()
                .map(usuario -> mapper.map(usuario, UserRespDTO.class))
                .collect(Collectors.toList());
    }

    public UserRespDTO obterPorId(Long id){
        Optional<User> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            throw new NekiException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optUsuario.get(), UserRespDTO.class);
    }


    public void deletar(Long id) {
        obterPorId(id);
        usuarioRepository.deleteById(id);
    }

    public UserRespDTO obterPorEmail(String email){
        Optional<User> optUsuario =  usuarioRepository.findByEmail(email);

        if(optUsuario.isEmpty()){
            throw new NekiException("Nenhum registro encontrado para o email: " + email);
        }

        return mapper.map(optUsuario.get(),UserRespDTO.class);
    }


    public UserRespDTO editarSenha(Long id, String novaSenha){
        User usuarioModel = mapper.map(obterPorId(id), User.class);
        String senha =  passwordEncoder.encode(novaSenha);
        usuarioModel.setSenha(senha);
        usuarioModel = usuarioRepository.save(usuarioModel);

        return mapper.map(usuarioModel, UserRespDTO.class);
    }

    public Boolean verificarSenhaAtual(String email, String senha){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
            return true;

        } catch (RuntimeException e){
            return false;
        }

    }

    public UserRespDTO atualizar(Long id, UserReqDTO usuarioRequest){
        cadastroService.haveEmail(usuarioRequest, id);
        UserRespDTO obterperfil = obterPorId(id);
        User usuarioModel = mapper.map(usuarioRequest, User.class);
        if(usuarioModel.getSenha() == null) {
            User obterSenhaAntiga = usuarioRepository.findById(id).get();
            usuarioModel.setSenha(obterSenhaAntiga.getSenha());
        }else {
            String senha =  passwordEncoder.encode(usuarioModel.getSenha());
            usuarioModel.setSenha(senha);
        }
        usuarioModel.setId(id);
        usuarioModel.setPerfil(obterperfil.getPerfil());
        usuarioModel = usuarioRepository.save(usuarioModel);

        return mapper.map(usuarioModel, UserRespDTO.class);
    }
}
