package com.octa.springapi.security;

import com.octa.springapi.model.Usuario;
import com.octa.springapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha invalidos"));
        return new User(email,usuario.getSenha(),getPermisoes(usuario));

    }

    private Collection<? extends GrantedAuthority> getPermisoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> autorities=new HashSet<>();
        usuario.getPermisaos().forEach((p)->autorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
        return autorities;
    }
}
