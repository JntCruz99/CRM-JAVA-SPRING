package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.dto.Login;
import br.com.fesvip.crm.dto.TokenResponse;
import br.com.fesvip.crm.entity.Usuario;
import br.com.fesvip.crm.service.UsuarioService;
import br.com.fesvip.crm.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public TokenResponse login(@RequestBody Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.username(),
                        login.senha());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Usuario) authenticate.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        Long expiresIn = tokenService.getRemainingTokenExpiration(token);

        TokenResponse tokenResponse = new TokenResponse(token, expiresIn, true);

        return tokenResponse;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuarioService.save(usuario);
        return usuario;
    }
}
