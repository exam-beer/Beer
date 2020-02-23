package no.exam.beer.controller;

import no.exam.beer.entity.Beer;
import no.exam.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeerController {
    private BeerService service;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.service = beerService;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/beers")
    private List<Beer> getAllBeers() {
        return service.getAllBeers();
    }

    @GetMapping("beers/{id}")
    private Beer getBeer(@PathVariable("id") int id) {
        return service.getBeerById(id);
    }

    @DeleteMapping("/beers/{id}")
    private void deleteBeer(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping(path = "/beers", consumes = MediaType.APPLICATION_JSON_VALUE)
    private long saveBeer(@RequestBody Beer beer) {
        service.saveOrUpdate(beer);
        return beer.getId();
    }
}
