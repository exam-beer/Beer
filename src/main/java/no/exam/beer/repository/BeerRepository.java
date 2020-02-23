package no.exam.beer.repository;

import no.exam.beer.entity.Beer;
import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Integer> {}
