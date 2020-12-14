package com.api.temperatura.domain.service;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.domain.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureService {
    @Autowired
    private TemperatureRepository temperatureRepository;

    public List<Temperature> getAll(){
        return temperatureRepository.getAll();
    }

    public Optional<List<Temperature>> getTemperatureByFecha(LocalDateTime TimeStart, LocalDateTime TimeEnd){
        return temperatureRepository.getAllByFechaTimeBetween(TimeStart,TimeEnd);
    }

    public Optional<Temperature> getTemperature(int idTemperature){
        return temperatureRepository.getTemperatura(idTemperature);
    }

    public Temperature save(Temperature temperature){
        return temperatureRepository.save(temperature);
    }

    public boolean delete(int idTemperature){

        return getTemperature(idTemperature).map(
                temperature -> {
                    temperatureRepository.delete(idTemperature);
                    return true;
                }
        ).orElse(false);
    }
}
