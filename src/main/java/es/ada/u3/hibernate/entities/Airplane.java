package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.LinkedHashSet;
import java.util.Set;
@Entity
public class Airplane {
    @Id
    @Column(name = "AIRPLANEREGNO")
    private Integer id;

    @OneToMany
    @JoinTable(
            name = "airport",
            joinColumns = @JoinColumn(name = "IDAIRPORT")
    )
    private Set<Airport> idairport;

    @ManyToOne
    @JoinTable(
            name = "airplanetype",
            joinColumns = @JoinColumn(name = "airplanetypename")
    )
    private AirplaneType airplaneType;

    public Airplane() {
        idairport = new LinkedHashSet<Airport>();
        airplaneType = new AirplaneType();
    }

}
