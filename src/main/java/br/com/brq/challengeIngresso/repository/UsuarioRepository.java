package br.com.brq.challengeIngresso.repository;

import br.com.brq.challengeIngresso.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, String> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
