package com.api.temperatura.domain;

import java.time.LocalDateTime;

public class Temperature {
    private Integer idTemperatura;
    private Integer medida;
    private String unidad;
    private LocalDateTime fecha;

    public Integer getIdTemperatura() {
        return idTemperatura;
    }

    public void setIdTemperatura(Integer idTemperatura) {
        this.idTemperatura = idTemperatura;
    }

    public Integer getMedida() {
        return medida;
    }

    public void setMedida(Integer medida) {
        this.medida = medida;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
