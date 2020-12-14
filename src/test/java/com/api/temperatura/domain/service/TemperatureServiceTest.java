package com.api.temperatura.domain.service;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.web.controller.TemperaturaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemperatureServiceTest {
    @Autowired
    TemperatureService temperatureService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getTemperatureByFecha() {
    }

    @Test
    void getTemperature() {

    }

    @Test
    void save() {
        Temperature temperature = new Temperature();
        temperature.setFecha(LocalDateTime.parse("2020-12-13T23:59:59"));
        temperature.setMedida(50);
        temperature.setUnidad("C");

        //when
        Temperature recieve = temperatureService.save(temperature);

        Temperature found = temperatureService.getTemperature(recieve.getIdTemperatura()).get();

        //then
        assertEquals(found.getMedida(),temperature.getMedida());

        assertEquals(found.getUnidad(),temperature.getUnidad());
    }

    @Test
    void delete() {
        //before
        Temperature temperature = new Temperature();
        temperature.setFecha(LocalDateTime.parse("2020-12-13T23:59:59"));
        temperature.setMedida(50);
        temperature.setUnidad("C");

        //when
        Temperature recieve = temperatureService.save(temperature);

        Boolean found = temperatureService.delete(recieve.getIdTemperatura());

        //then
        assertEquals(found,true);
    }
}