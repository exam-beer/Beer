package no.exam.beer;

import no.exam.beer.service.BeerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeerApplication {

	private BeerService beerService;

	public static void main(String[] args) {
		SpringApplication.run(BeerApplication.class, args);

		//ApplicationContext appContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

		//BeerService service = (BeerService) appContext.getBean("BeerService");
	}
}
