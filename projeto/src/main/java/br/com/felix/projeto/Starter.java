package br.com.felix.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);

		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256 );

		Map<String, PasswordEncoder> encoders = new HashMap<>();
			encoders.put("pbkdf2", pbkdf2Encoder);
			DelegatingPasswordEncoder passwordEncoder =
					new DelegatingPasswordEncoder("pbkdf2", encoders);
			passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);


			String result1 = passwordEncoder.encode("admin123");
			String result2 = passwordEncoder.encode("admin234");
			System.out.println("My Hash result1" + result1);
			System.out.println("My Hash result2" + result2);
	}

	}

