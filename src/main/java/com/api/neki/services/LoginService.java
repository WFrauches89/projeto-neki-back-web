package com.api.neki.services;
import com.api.neki.dto.LoginRespDTO;
import com.api.neki.dto.UserRespDTO;
import com.api.neki.entities.User;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.repository.UserRepository;
import com.api.neki.security.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class LoginService {

    private static final String BEARER = "Bearer ";

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public LoginRespDTO logar(String email, String senha){
        try{
            Authentication autenticacao = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
            String token =  BEARER + jwtService.gerarToken(autenticacao);
            UserRespDTO usuarioResponse = obterPorEmail(email);
            return new LoginRespDTO(token, usuarioResponse);

        } catch (RuntimeException e){
            throw new NekiException("E-mail ou senha incorretos.");
        }

    }

    public UserRespDTO obterPorEmail(String email){
        Optional<User> optUsuario =  usuarioRepository.findByEmail(email);

        if(optUsuario.isEmpty()){
            throw new NekiException("Nenhum registro encontrado para o email: " + email);
        }

        return mapper.map(optUsuario.get(),UserRespDTO.class);
    }
}
