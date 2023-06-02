package br.com.brq.challengeIngresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ChallengeIngressoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeIngressoApplication.class, args);
	}

}
