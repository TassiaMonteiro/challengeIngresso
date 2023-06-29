package br.com.brq.challengeIngresso.dataprovider.repository;

import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, String> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
