package com.api.neki.services;

import com.api.neki.dto.UserReqDTO;
import com.api.neki.dto.UserRespDTO;
import com.api.neki.entities.ETipoPerfil;
import com.api.neki.entities.User;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserRespDTO createUser(UserReqDTO usuarioRequest){
        haveEmail(usuarioRequest, 0L);
        User usuarioModel = mapper.map(usuarioRequest, User.class);
        String senha =  passwordEncoder.encode(usuarioModel.getSenha());
        usuarioModel.setSenha(senha);

        if (usuarioRepository.count() == 0) {
            usuarioModel.setPerfil(ETipoPerfil.NORMAL);
        }

        usuarioModel = usuarioRepository.save(usuarioModel);

        return mapper.map(usuarioModel, UserRespDTO.class);
    }

    public void haveEmail(UserReqDTO usuarioRequest, Long id){
        List<UserRespDTO> listaUsuarioResponse = getAllUsers();

        for (UserRespDTO usuarioResponse : listaUsuarioResponse){
            if(usuarioResponse.getEmail().equals(usuarioRequest.getEmail()) && usuarioResponse.getId() != id){
                throw new NekiException("E-mail já cadastrado!");
            }
        }
    }

    public List<UserRespDTO> getAllUsers(){
        List<User> usuarios = usuarioRepository.findAll();

        return usuarios
                .stream()
                .map(usuario -> mapper.map(usuario, UserRespDTO.class))
                .collect(Collectors.toList());
    }
}
