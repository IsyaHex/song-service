package uz.epam.msa.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("uz.epam.msa.song.repository")
@EntityScan("uz.epam.msa.song.domain")
@EnableEurekaClient
public class SongServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SongServiceApplication.class, args);
	}

}
