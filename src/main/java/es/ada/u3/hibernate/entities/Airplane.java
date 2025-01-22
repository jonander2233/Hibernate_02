package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.LinkedHashSet;

@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "AIRPLANEREGNO", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDAIRPORT", nullable = false)
    private Airport idairport;

    @ManyToOne
    @JoinColumn(name = "AIRPLANETYPENAME", nullable = false)
    private AirplaneType airplaneType;

    public Airplane() {
        idairport = new Airport();
        airplaneType = new AirplaneType();
    }

    public Airplane(Integer id, Airport idairport, AirplaneType airplaneType) {
        this.id = id;
        if(idairport != null){
            this.idairport = idairport;
        }else {
            this.idairport = new Airport();
        }
        if(airplaneType != null){
            this.airplaneType = airplaneType;
        }else{
            this.airplaneType = new AirplaneType();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getIdairport() {
        return idairport;
    }

    public void setIdairport(Airport idairport) {
        this.idairport = idairport;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
}
