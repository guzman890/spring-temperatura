package com.api.temperatura.persistence.crud;

import com.api.temperatura.persistence.entity.Temperatura;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperaturaCrudRepository extends CrudRepository<Temperatura,Integer> {

    List<Temperatura> findByUnidad(int unidad);

    List<Temperatura> findAllByFechaBetween(
            LocalDateTime publicationTimeStart,
            LocalDateTime publicationTimeEnd
    );
}
