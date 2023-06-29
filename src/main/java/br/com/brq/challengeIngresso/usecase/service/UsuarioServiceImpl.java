package br.com.brq.challengeIngresso.usecase.service;

import br.com.brq.challengeIngresso.usecase.exception.EntidadeEmConflitoException;
import br.com.brq.challengeIngresso.usecase.exception.NegocioException;
import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDtoViaCep;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import br.com.brq.challengeIngresso.usecase.domain.RecuperarSenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.SenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;
import br.com.brq.challengeIngresso.usecase.exception.CepNaoEncontrado;
import br.com.brq.challengeIngresso.usecase.gateway.UsuarioGateway;
import br.com.brq.challengeIngresso.usecase.mapper.EnderecoViaCepUseCaseMapperRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioUseCase {

    @Autowired
    UsuarioGateway usuarioGateway;

    @Override
    public UsuarioDomain cadastrar(UsuarioDomain usuarioDomain) {
        usuarioDomain.setId(UUID.randomUUID().toString());
        validarCadastro(usuarioDomain);
        buscaEndereco(usuarioDomain);
        validarEndereco(usuarioDomain);
        return usuarioGateway.cadastrar(usuarioDomain);
    }

    @Override
    public List<UsuarioDomain> listarUsuarios() {
        return usuarioGateway.listarUsuarios();
    }

    @Override
    public UsuarioDomain buscarId(String id) {
        return usuarioGateway.detalharUsuario(id);
    }

    @Override
    public void removerUsuario(String id) {
        usuarioGateway.removerUsuario(id);
    }

    @Override
    public UsuarioDomain atualizarUsuario(String id, UsuarioDomain usuarioRequest) {
        UsuarioDomain usuarioExistente = buscarId(id);

        if(StringUtils.isNotBlank(usuarioRequest.getNomeCompleto())){
            usuarioExistente.setNomeCompleto(usuarioRequest.getNomeCompleto());
        }

        if(StringUtils.isNotBlank(usuarioRequest.getApelido())){
            usuarioExistente.setApelido(usuarioRequest.getApelido());
        }

        if(StringUtils.isNotBlank(usuarioRequest.getDataNascimento())){
            usuarioExistente.setDataNascimento(usuarioRequest.getDataNascimento());
        }

        if(Objects.nonNull(usuarioRequest.getCelular())){
            usuarioExistente.setCelular(usuarioRequest.getCelular());
        }

        if(StringUtils.isNotBlank(usuarioRequest.getSexo())){
            usuarioExistente.setSexo(usuarioRequest.getSexo());
        }

        if (Objects.isNull(usuarioRequest.getEndereco())){
            usuarioRequest.setEndereco(usuarioExistente.getEndereco());
        }

        if(Objects.nonNull(usuarioRequest.getEndereco().getCep())) {
            buscaEndereco(usuarioRequest);
            validarEndereco(usuarioRequest);
            usuarioExistente.setEndereco(usuarioRequest.getEndereco());
        } else {

            if (StringUtils.isBlank(usuarioRequest.getEndereco().getCep())){
                usuarioRequest.getEndereco().setCep(usuarioExistente.getEndereco().getCep());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getLogradouro())){
                usuarioRequest.getEndereco().setLogradouro(usuarioExistente.getEndereco().getLogradouro());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getNumero())){
                usuarioRequest.getEndereco().setNumero(usuarioExistente.getEndereco().getNumero());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getBairro())){
                usuarioRequest.getEndereco().setBairro(usuarioExistente.getEndereco().getBairro());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getCidade())){
                usuarioRequest.getEndereco().setCidade(usuarioExistente.getEndereco().getCidade());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getEstado())){
                usuarioRequest.getEndereco().setEstado(usuarioExistente.getEndereco().getEstado());
            }
            if (StringUtils.isBlank(usuarioRequest.getEndereco().getPais())){
                usuarioRequest.getEndereco().setPais(usuarioExistente.getEndereco().getPais());
            }

            usuarioExistente.setEndereco(usuarioRequest.getEndereco());
        }

        return usuarioGateway.atualizarUsuario(usuarioExistente);
    }

    @Override
    public void alterarSenha(String id, SenhaDomain senha) {
        UsuarioDomain usuarioDomain = buscarId(id);

        if (usuarioDomain.senhaNaoCoincideCom(senha.getSenhaAtual())) {
            throw new NegocioException("Senha informada não coincide com a senha do usuário.");
        }
        usuarioDomain.setSenha(senha.getNovaSenha());
        usuarioGateway.atualizarUsuario(usuarioDomain);
    }

    @Override
    public UsuarioDomain gerarCodigoAlteracaoSenha(UsuarioDomain usuarioDomain) {
        String uuid = UUID.randomUUID().toString();
        usuarioDomain.setCodigoSeguranca(uuid);

        usuarioDomain.setDataHoraCodigoSeguranca(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0));

        return usuarioGateway.atualizarUsuario(usuarioDomain);
    }

    @Override
    public UsuarioDomain novaSenha(String id, RecuperarSenhaDomain senhaDomain) {
        UsuarioDomain usuarioDomain = buscarId(id);
        isCodigoValido(senhaDomain.getCodigoSeguranca(), usuarioDomain);
        isdataHoraCodigoSegurancaValida(usuarioDomain);
        isNovaSenhaValida(senhaDomain.getNovaSenha(), usuarioDomain);

        usuarioDomain.setSenha(senhaDomain.getNovaSenha());
        return usuarioGateway.atualizarUsuario(usuarioDomain);
    }

    private void isNovaSenhaValida(String novaSenha, UsuarioDomain usuarioDomain) {
        if(novaSenha.equals(usuarioDomain.getSenha())){
            throw new NegocioException("Nova senha não pode ser igual à senha anterior.");
        }
    }

    private void isdataHoraCodigoSegurancaValida(UsuarioDomain usuarioDomain) {
        OffsetDateTime dataInicio = usuarioDomain.getDataHoraCodigoSeguranca();
        OffsetDateTime dataFim = dataInicio.plus(5, ChronoUnit.MINUTES);
        OffsetDateTime dataAtual = OffsetDateTime.now();

        if (dataAtual.isAfter(dataFim)){
            throw new NegocioException("Seu código de segurança expirou.");
        }
    }

    private void isCodigoValido(String codigoSeguranca, UsuarioDomain usuarioDomain) {
        if(!codigoSeguranca.equals(usuarioDomain.getCodigoSeguranca())){
            throw new NegocioException("Codigo de segurança incorreto.");
        }
    }

    private void validarCadastro(UsuarioDomain usuario){

        if (usuarioGateway.verificaCpfExiste(usuario.getCpf())){
            throw new EntidadeEmConflitoException(String.format("O CPF %s já está cadastrado", usuario.getCpf()));
        }
        if (usuarioGateway.verificaEmailExiste(usuario.getEmail())){
            throw new EntidadeEmConflitoException(String.format("O email %s já está cadastrado",usuario.getEmail()));
        }
    }

    private void buscaEndereco(UsuarioDomain usuarioDomain){

        EnderecoDtoViaCep enderecoCorreio = usuarioGateway.executa(usuarioDomain.getEndereco().getCep());

        EnderecoDomain enderecoDomain = EnderecoViaCepUseCaseMapperRequest.converteToDomain(enderecoCorreio);

        if (StringUtils.isBlank(enderecoCorreio.getLogradouro())){
            enderecoDomain.setLogradouro(usuarioDomain.getEndereco().getLogradouro());;
        } else {
            enderecoDomain.setLogradouro(enderecoCorreio.getLogradouro());
        }
        if (StringUtils.isBlank(enderecoCorreio.getBairro())){
            enderecoDomain.setBairro(usuarioDomain.getEndereco().getBairro());;
        } else {
            enderecoDomain.setBairro(enderecoCorreio.getBairro());
        }

        enderecoDomain.setComplemento(usuarioDomain.getEndereco().getComplemento());
        enderecoDomain.setNumero(usuarioDomain.getEndereco().getNumero());
        enderecoDomain.setPais(new Locale("pt", "BR").getCountry());
        usuarioDomain.setEndereco(enderecoDomain);
    }

    private void validarEndereco(UsuarioDomain usuarioDomain){
        if (StringUtils.isBlank(usuarioDomain.getEndereco().getLogradouro())){
            throw new NegocioException("O campo logradouro deve ser preenchido.");
        }
        if (StringUtils.isBlank(usuarioDomain.getEndereco().getBairro())){
            throw new NegocioException("O campo bairro deve ser preenchido.");
        }
    }

}
