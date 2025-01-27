package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "AIRPLANEREGNO", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDAIRPORT", nullable = false)
    private Airport airport;

    @ManyToOne
    @JoinColumn(name = "AIRPLANETYPENAME", nullable = false)
    private AirplaneType airplaneType;

    public Airplane() {
        airport = new Airport();
        airplaneType = new AirplaneType();
    }

    public Airplane(Integer id, Airport airport, AirplaneType airplaneType) {
        this.id = id;
        if(airport != null){
            this.airport = airport;
            airport.addAirplane(this);
        }else {
            this.airport = new Airport();
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
//

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", airport=" + airport.getId() +
                ", airplaneType=" + airplaneType +
                '}';
    }
}
