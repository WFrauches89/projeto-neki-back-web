package com.api.neki.entities;

import com.api.neki.common.ConversorData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long Id;

    @Column(nullable = false)
    private String nameUser;

    @Column(nullable = false, unique = true)
    private String email;

    //@Column
    private String pass;

    @Column(nullable = false)
    private String dtCadastro;

    @Column(nullable = false)
    private ETipoPerfil perfil;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skills> skills;

    public User() {
        this.dtCadastro = ConversorData.converterDateParaDataHora(new Date());
        this.perfil = ETipoPerfil.NORMAL;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

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

    public String getSenha() {
        return pass;
    }

    public void setSenha(String senha) {
        this.pass = senha;
    }

    public String getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(String dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public ETipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> perfis = new ArrayList<>();
        perfis.add(perfil.toString());
        return perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
