package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "airplanetype")
public class AirplaneType {
    @Id
    @Column(name = "AIRPLANETYPENAME",nullable = false)
    private String airplaneTypeName;

    @Column(name = "AIRPLANETYPESIZE")
    private Integer airplaneTypeSize;

    @OneToMany(mappedBy = "airplaneType")
    private Set<Airplane> airplanes = new LinkedHashSet<Airplane>();

    public AirplaneType(String airplaneTypeName, Integer airplaneTypeSize) {
        this.airplaneTypeName = airplaneTypeName;
        this.airplaneTypeSize = airplaneTypeSize;
    }

    public AirplaneType() {

    }

    public String getAirplaneTypeName() {
        return airplaneTypeName;
    }

    public void setAirplaneTypeName(String airplaneTypeName) {
        this.airplaneTypeName = airplaneTypeName;
    }

    public Integer getAirplaneTypeSize() {
        return airplaneTypeSize;
    }

    public void setAirplaneTypeSize(Integer airplaneTypeSize) {
        this.airplaneTypeSize = airplaneTypeSize;
    }
}
