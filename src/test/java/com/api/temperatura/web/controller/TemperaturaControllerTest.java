package com.api.temperatura.web.controller;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.domain.dto.EscalaFecha;
import com.api.temperatura.domain.dto.RangoFecha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemperaturaControllerTest {
    @Autowired
    TemperaturaController temperaturaController;

    @Test
    void getTemperaturesByFechaRango() {
        Temperature temperature1 = new Temperature();
        temperature1.setFecha(LocalDateTime.parse("2020-12-13T20:59:59"));
        temperature1.setMedida(10);
        temperature1.setUnidad("C");

        Temperature temperature2 = new Temperature();
        temperature2.setFecha(LocalDateTime.parse("2020-12-13T20:59:59"));
        temperature2.setMedida(20);
        temperature2.setUnidad("C");

        Temperature temperature3 = new Temperature();
        temperature3.setFecha(LocalDateTime.parse("2020-12-13T20:59:59"));
        temperature3.setMedida(30);
        temperature3.setUnidad("C");

        Temperature temperature4 = new Temperature();
        temperature4.setFecha(LocalDateTime.parse("2020-12-13T20:59:59"));
        temperature4.setMedida(40);
        temperature4.setUnidad("C");

        RangoFecha RangoFecha = new RangoFecha();
        RangoFecha.setDate("2020-12-13");
        RangoFecha.setMax(40);
        RangoFecha.setMin(10);
        RangoFecha.setAverage(25.0);

        temperaturaController.save(temperature1);
        temperaturaController.save(temperature2);
        temperaturaController.save(temperature3);
        temperaturaController.save(temperature4);

        RangoFecha rangoFechaFound = temperaturaController.getTemperaturesByFecha("2020-12-13");

        //then
        assertEquals(RangoFecha.getMax(),rangoFechaFound.getMax());

        assertEquals(RangoFecha.getMin(),rangoFechaFound.getMin());

        assertEquals(RangoFecha.getAverage(),rangoFechaFound.getAverage());
    }

    @Test
    void getTemperaturesByFechaEscalar() {
        Temperature temperature1 = new Temperature();
        temperature1.setFecha(LocalDateTime.parse("2020-12-13T18:49:59"));
        temperature1.setMedida(10);
        temperature1.setUnidad("C");

        Temperature temperature2 = new Temperature();
        temperature2.setFecha(LocalDateTime.parse("2020-12-13T18:39:59"));
        temperature2.setMedida(20);
        temperature2.setUnidad("C");

        Temperature temperature3 = new Temperature();
        temperature3.setFecha(LocalDateTime.parse("2020-12-13T21:29:59"));
        temperature3.setMedida(30);
        temperature3.setUnidad("C");

        Temperature temperature4 = new Temperature();
        temperature4.setFecha(LocalDateTime.parse("2020-12-13T21:19:59"));
        temperature4.setMedida(40);
        temperature4.setUnidad("C");

        EscalaFecha escalaFecha20 =  new EscalaFecha();
        escalaFecha20.setTime("18:00 19:00");
        escalaFecha20.setMin(10);
        escalaFecha20.setMax(20);
        escalaFecha20.setAverage(15.0);

        EscalaFecha escalaFecha21 =  new EscalaFecha();
        escalaFecha21.setTime("21:00 22:00");
        escalaFecha21.setMin(30);
        escalaFecha21.setMax(40);
        escalaFecha21.setAverage(35.0);

        temperaturaController.save(temperature1);
        temperaturaController.save(temperature2);
        temperaturaController.save(temperature3);
        temperaturaController.save(temperature4);

        List<EscalaFecha> escalaFechasFound = temperaturaController.getTemperaturesByFechaEscalar("2020-12-13");

        //then
        EscalaFecha escalaFechaFound20= escalaFechasFound.stream()
                .filter(escalaFecha -> "18:00 19:00".equals(escalaFecha.getTime()))
                .findAny()
                .orElse(null);

        assertEquals(escalaFechaFound20.getMax(),escalaFecha20.getMax());
        assertEquals(escalaFechaFound20.getMin(),escalaFecha20.getMin());
        assertEquals(escalaFechaFound20.getAverage(),escalaFecha20.getAverage());

        EscalaFecha escalaFechaFound21= escalaFechasFound.stream()
                .filter(escalaFecha -> "21:00 22:00".equals(escalaFecha.getTime()))
                .findAny()
                .orElse(null);
        assertEquals(escalaFechaFound21.getMax(),escalaFecha21.getMax());
        assertEquals(escalaFechaFound21.getMin(),escalaFecha21.getMin());
        assertEquals(escalaFechaFound21.getAverage(),escalaFecha21.getAverage());
    }

    @Test
    void save() {
        //before
        Temperature temperature = new Temperature();
        temperature.setFecha(LocalDateTime.parse("2020-12-13T23:59:59"));
        temperature.setMedida(50);
        temperature.setUnidad("C");

        //when
        Temperature recieve = temperaturaController.save(temperature);

        Temperature found = temperaturaController.getTemperature(recieve.getIdTemperatura()).get();

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
        Temperature recieve = temperaturaController.save(temperature);

        Boolean found = temperaturaController.delete(recieve.getIdTemperatura());

        //then
        assertEquals(found,true);

    }
}