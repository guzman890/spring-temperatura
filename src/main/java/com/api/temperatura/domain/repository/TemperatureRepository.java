package com.api.temperatura.domain.repository;

import com.api.temperatura.domain.Temperature;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TemperatureRepository {

    List<Temperature> getAll();
    Optional<List<Temperature>> getByUnidad(int unidad);
    Optional<List<Temperature>> getAllByFechaTimeBetween(LocalDateTime TimeStart, LocalDateTime TimeEnd);
    Optional<Temperature> getTemperatura(int id);
    Temperature save(Temperature temperatura);
    void  delete(int id);

}
