package com.api.temperatura.persistence;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.domain.repository.TemperatureRepository;
import com.api.temperatura.persistence.crud.TemperaturaCrudRepository;
import com.api.temperatura.persistence.entity.Temperatura;
import com.api.temperatura.persistence.mapper.TemperatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TemperaturaRepository implements TemperatureRepository {
    @Autowired
    private TemperaturaCrudRepository temperaturaCrudRepository;
    @Autowired
    private TemperatureMapper mapper;

    @Override
    public List<Temperature> getAll(){
        List<Temperatura> lista = (List<Temperatura>) temperaturaCrudRepository.findAll();

        return mapper.toTemperatures(lista);
    }

    @Override
    public Optional<List<Temperature>> getByUnidad(int unidad){
        List<Temperatura> lista = (List<Temperatura>) temperaturaCrudRepository.findByUnidad(unidad);
        return Optional.of( mapper.toTemperatures(lista) );
    }

    @Override
    public Optional<List<Temperature>> getAllByFechaTimeBetween(LocalDateTime TimeStart,
                                                                LocalDateTime TimeEnd){
        List<Temperatura> lista = (List<Temperatura>) temperaturaCrudRepository
                                                        .findAllByFechaBetween(TimeStart,TimeEnd);
        return Optional.of( mapper.toTemperatures(lista) );
    }

    @Override
    public Optional<Temperature> getTemperatura(int id){
        return temperaturaCrudRepository.findById(id).map(
                temperatura -> mapper.toTemperature(temperatura)
        );
    }

    @Override
    public Temperature save(Temperature temperature){
        Temperatura temperatura = mapper.toTemperatura(temperature);
        return mapper.toTemperature(temperaturaCrudRepository.save(temperatura));
    }

    @Override
    public void  delete(int id){
        temperaturaCrudRepository.deleteById(id);
    }
}
