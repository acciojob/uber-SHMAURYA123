package com.driver.model;



import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private int perKmRate;
    private Boolean availabile;

    @JoinColumn
    @OneToOne
    private Driver driver;

    public Cab() {
    }

    public Cab(int id, int perKmRate, Boolean availabile, Driver driver) {
        Id = id;
        this.perKmRate = perKmRate;
        this.availabile = availabile;
        this.driver = driver;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
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
