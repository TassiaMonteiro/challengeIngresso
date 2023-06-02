package br.com.brq.challengeIngresso.feign;

import br.com.brq.challengeIngresso.api.models.dto.EnderecoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepFeign {

    @GetMapping("{cep}/json")
    EnderecoDto buscaEnderecoCep(@PathVariable("cep") String cep);
}
