package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    Usuario findById(Long id);

    List<Usuario> findAll();
    Usuario findByUsername(String username);

    void delete();
}
