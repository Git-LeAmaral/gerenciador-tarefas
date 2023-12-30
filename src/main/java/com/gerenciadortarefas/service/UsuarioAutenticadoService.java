package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioAutenticadoService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = iUsuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não foi encontrado"));

        List<SimpleGrantedAuthority> roles = usuario.getRoles()
                .stream()
                .map( role -> new SimpleGrantedAuthority(role.getNome().toString()))
                .collect(Collectors.toList());
        return  new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
