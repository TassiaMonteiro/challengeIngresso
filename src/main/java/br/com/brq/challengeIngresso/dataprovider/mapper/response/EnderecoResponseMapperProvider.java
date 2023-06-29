package br.com.brq.challengeIngresso.dataprovider.mapper.response;

import br.com.brq.challengeIngresso.dataprovider.entities.Endereco;
import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnderecoResponseMapperProvider {

    public static EnderecoDomain convertToModel(Endereco endereco){

        return EnderecoDomain.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .pais(endereco.getPais())
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .build();
    }
}
