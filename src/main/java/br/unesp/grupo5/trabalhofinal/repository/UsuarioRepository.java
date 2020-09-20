package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCpf(String cpf);

    Usuario findByEmail(String email);
}
