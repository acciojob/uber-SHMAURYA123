package com.driver.model;



import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabId;
    private Integer perKmRate;
    private Boolean availability;

    @JoinColumn
    @OneToOne
    private Driver driver;

    public Cab() {
    }

    public Cab(Integer cabId, Integer perKmRate, Boolean availability, Driver driver) {
        this.cabId = cabId;
        this.perKmRate = perKmRate;
        this.availability = availability;
        this.driver = driver;
    }

    public Integer getCabId() {
        return cabId;
    }

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
