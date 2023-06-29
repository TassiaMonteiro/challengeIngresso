package br.com.brq.challengeIngresso.dataprovider.mapper.request;

import br.com.brq.challengeIngresso.dataprovider.entities.Endereco;
import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.challengeIngresso.dataprovider.enums.Sexo;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

public class EnderecoRequestMapperProvider {

    public static Endereco convertToEntity(EnderecoDomain enderecoDomain){

        return Endereco.builder()
                .logradouro(enderecoDomain.getLogradouro())
                .numero(enderecoDomain.getNumero())
                .bairro(enderecoDomain.getBairro())
                .cidade(enderecoDomain.getCidade())
                .estado(enderecoDomain.getEstado())
                .pais(enderecoDomain.getPais())
                .cep(enderecoDomain.getCep())
                .complemento(enderecoDomain.getComplemento())
                .build();
    }
}
