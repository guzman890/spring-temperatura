package com.api.temperatura.web.controller;

import com.api.temperatura.domain.Temperature;
import com.api.temperatura.domain.dto.EscalaFecha;
import com.api.temperatura.domain.dto.RangoFecha;
import com.api.temperatura.domain.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/temperatura")
public class TemperaturaController {

    @Autowired
    private TemperatureService temperatureService;

    @GetMapping("/")
    public List<Temperature> getAll(){
        return temperatureService.getAll();
    }

    @GetMapping("/{idTemperature}")
    public Optional<Temperature> getTemperature(@PathVariable("idTemperature") int idTemperature){
        return temperatureService.getTemperature(idTemperature);
    }

    @GetMapping("/fecha/{date}")
    public Optional<List<Temperature>> getTemperatureByFecha(@PathVariable("date") String fecha){

        LocalDateTime LocalTimeStart = LocalDateTime.parse(fecha+"T00:00:00");

        LocalDateTime LocalTimeEnd = LocalDateTime.parse(fecha+"T23:59:59");

        return temperatureService.getTemperatureByFecha(LocalTimeStart,LocalTimeEnd);
    }

    @GetMapping("/rangofecha/{date}")
    public RangoFecha getTemperaturesByFecha(@PathVariable("date") String fecha){

        LocalDateTime LocalTimeStart = LocalDateTime.parse(fecha+"T00:00:00");

        LocalDateTime LocalTimeEnd = LocalDateTime.parse(fecha+"T23:59:59");

        Optional<List<Temperature>> lista = temperatureService.getTemperatureByFecha(LocalTimeStart,LocalTimeEnd);

        RangoFecha rangoFecha =  new RangoFecha();
        rangoFecha.setDate(fecha);
        rangoFecha.setMin(Integer.MAX_VALUE);
        rangoFecha.setMax(Integer.MIN_VALUE);
        rangoFecha.setAverage(0.0);

        lista.get().stream().forEach(temperature -> {
            if(temperature.getMedida() >= rangoFecha.getMax()){
                rangoFecha.setMax(temperature.getMedida());
            }
            if(temperature.getMedida() <= rangoFecha.getMin()){
                rangoFecha.setMin(temperature.getMedida());
            }
            rangoFecha.setAverage( rangoFecha.getAverage()+temperature.getMedida() );
        });

        rangoFecha.setAverage( rangoFecha.getAverage()/lista.get().size() );

        return rangoFecha;
    }

    @GetMapping("/rangofechaescalar/{date}")
    public List<EscalaFecha> getTemperaturesByFechaEscalar(@PathVariable("date") String fecha){

        LocalDateTime LocalTimeStart = LocalDateTime.parse(fecha+"T00:00:00");

        LocalDateTime LocalTimeEnd = LocalDateTime.parse(fecha+"T23:59:59");

        Optional<List<Temperature>> lista = temperatureService.getTemperatureByFecha(LocalTimeStart,LocalTimeEnd);

        Hashtable<Integer, EscalaFecha> escalas = new Hashtable<Integer, EscalaFecha>();

        lista.get().stream().forEach(temperature -> {

            EscalaFecha escalaFecha;

            if( !escalas.containsKey( temperature.getFecha().getHour() ) ){

                escalas.put( temperature.getFecha().getHour(), new EscalaFecha() );
                escalaFecha = escalas.get( temperature.getFecha().getHour() );
                escalaFecha.setTime(
                    temperature.getFecha().getHour()+":00 "+
                    ( temperature.getFecha().getHour()+1 )+":00"
                );
                escalaFecha.setMin(Integer.MAX_VALUE);
                escalaFecha.setMax(Integer.MIN_VALUE);
                escalaFecha.setAverage(0.0);

            }

            escalaFecha = escalas.get( temperature.getFecha().getHour() );

            if(temperature.getMedida() >= escalaFecha.getMax()){
                escalaFecha.setMax(temperature.getMedida());
            }
            if(temperature.getMedida() <= escalaFecha.getMin()){
                escalaFecha.setMin(temperature.getMedida());
            }
            escalaFecha.setAverage( escalaFecha.getAverage()+temperature.getMedida() );
            escalaFecha.add();
        });

        List<EscalaFecha> listEscala = escalas.values().stream().collect(Collectors.toList());

        listEscala.forEach(escala -> {
            escala.setAverage( escala.getAverage()/escala.count() );
        });

        return listEscala;
    }

    @PostMapping("/save")
    public Temperature save(@RequestBody Temperature temperature){
        return temperatureService.save(temperature);
    }

    @DeleteMapping("/delete/{idTemperature}")
    public boolean delete( @PathVariable("idTemperature") int idTemperature){
        return temperatureService.delete(idTemperature);
    }
}