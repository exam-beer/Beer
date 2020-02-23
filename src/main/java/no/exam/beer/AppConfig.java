package no.exam.beer;

import no.exam.beer.repository.BeerRepository;
import no.exam.beer.service.BeerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("main")
    public BeerRepository beerRepository() {
        return new BeerService();
    }
}
