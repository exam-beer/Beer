package no.exam.beer.service;

import no.exam.beer.entity.Beer;
import no.exam.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeerService implements BeerRepository {

    @Autowired
    private BeerRepository repository;

    @Transactional
    public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<Beer>();
        repository.findAll().forEach(beers::add);
        return beers;
    }

    @Transactional
    public Beer getBeerById(int id) {
        return repository.findById(id).get();
    }

    @Transactional
    public void saveOrUpdate(Beer beer) {
        List<Beer> beers = new ArrayList<Beer>();
        repository.findAll().forEach(p -> beers.add(p));

        beer.setId(beers.size() + 1);

        repository.save(beer);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public <S extends Beer> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Beer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Beer> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Beer> findAll() {
        return null;
    }

    @Override
    public Iterable<Beer> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Beer entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Beer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
