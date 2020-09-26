package br.unesp.grupo5.trabalhofinal.security;

import br.unesp.grupo5.trabalhofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioSerive;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/usuario/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/conteudo/**").hasAnyAuthority("funcionario", "usuario")
                .antMatchers(HttpMethod.GET, "/api/serie/**").hasAnyAuthority("funcionario", "usuario")
                .antMatchers(HttpMethod.GET, "/api/genero/**").hasAnyAuthority("funcionario", "usuario")
                .antMatchers(HttpMethod.GET, "/api/avaliacao/**").hasAnyAuthority("funcionario", "usuario")
                .antMatchers(HttpMethod.GET, "/api/comentario/**").hasAnyAuthority("funcionario", "usuario")
                .antMatchers(HttpMethod.GET, "/uploaded/**").permitAll()
                .antMatchers("/**").hasAuthority("funcionario")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        System.out.println("Segurança configurada!");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioSerive);
    }
}
