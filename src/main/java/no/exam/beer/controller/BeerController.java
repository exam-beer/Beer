package no.exam.beer.controller;

import io.micrometer.core.annotation.Timed;
import no.exam.beer.entity.Beer;
import no.exam.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Timed
public class BeerController {
    private BeerService service;

    protected Logger logger = Logger.getLogger(BeerController.class.getName());

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.service = beerService;
    }

    private void watchBeers() {
        Counter counter = Metrics.counter("watch.beers", "state", "watched");
        counter.increment();
    }

    @GetMapping("/beers")
    private List<Beer> getAllBeers() {
        logger.info(String.format("Beer.getAllBeers()"));
        watchBeers();
        return service.getAllBeers();
    }

    @GetMapping("beers/{id}")
    private Beer getBeer(@PathVariable("id") int id) {
        logger.info(String.format("Beer.getBeer(%d)", id));
        return service.getBeerById(id);
    }

    @DeleteMapping("/beers/{id}")
    private void deleteBeer(@PathVariable("id") int id) {
        logger.info(String.format("Beer.deleteBeer(%d)"));
        service.delete(id);
    }

    @PostMapping(path = "/beers", consumes = MediaType.APPLICATION_JSON_VALUE)
    private long saveBeer(@RequestBody Beer beer) {
        logger.info(String.format("Beer.saveBeer(%s)", beer));
        service.saveOrUpdate(beer);
        return beer.getId();
    }
}
