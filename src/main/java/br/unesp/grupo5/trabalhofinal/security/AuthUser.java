package br.unesp.grupo5.trabalhofinal.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUser extends User {
    private long idUsuario;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long idUsuario) {
        super(username, password, authorities);
        this.idUsuario = idUsuario;
    }
    
}
