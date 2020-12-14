package com.api.temperatura.persistence.mapper;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.persistence.entity.Temperatura;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemperatureMapper {
    @Mappings(
        {
            @Mapping(source="id", target = "idTemperatura" ),
            @Mapping(source="medida", target = "medida" ),
            @Mapping(source="unidad", target = "unidad" ),
            @Mapping(source="fecha", target = "fecha" ),
        }
    )
    Temperature toTemperature(Temperatura temperatura);
    List<Temperature> toTemperatures(List<Temperatura> temperatura);

    @InheritInverseConfiguration
    Temperatura toTemperatura(Temperature temperature);
}
