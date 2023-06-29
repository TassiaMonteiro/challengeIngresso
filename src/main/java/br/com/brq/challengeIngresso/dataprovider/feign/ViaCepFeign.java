package br.com.brq.challengeIngresso.dataprovider.feign;

import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDtoViaCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepFeign {

    @GetMapping("{cep}/json")
    EnderecoDtoViaCep buscaEnderecoCep(@PathVariable("cep") String cep);
}
