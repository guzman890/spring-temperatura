package com.api.temperatura.domain.dto;

public class EscalaFecha {
    private String time;
    private Integer min;
    private Integer max;
    private Double average;

    private Integer count = 0;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }


    public void add(){
        count++;
    }
    public Integer count(){
        return count;
    }
}
