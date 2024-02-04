package com.driver.model;



import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer perKmRate;
    private Boolean availabile;

    @JoinColumn
    @OneToOne
    private Driver driver;

    public Cab() {
    }

    public Cab(Integer id, Integer perKmRate, Boolean availabile, Driver driver) {
        this.id = id;
        this.perKmRate = perKmRate;
        this.availabile = availabile;
        this.driver = driver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public Boolean getAvailabile() {
        return availabile;
    }

    public void setAvailabile(Boolean availabile) {
        this.availabile = availabile;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
