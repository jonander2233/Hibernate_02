package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "IDAIRPORT", nullable = false)
    private String id;

    @Column(name = "AIRPORTCAPACITY")
    private int capacity;

    @OneToMany(mappedBy = "idairport")
    private Set<Airplane> airplanes = new LinkedHashSet<Airplane>();

    @ManyToMany
    @JoinTable(
            name = "neighbor",
            joinColumns = @JoinColumn(name = "IDAIRPORT_A"),
            inverseJoinColumns = @JoinColumn(name = "IDAIRPORT_B")
    )
    private Set<Airport> neighbor = null;

    @ManyToMany(mappedBy = "neighbor")
    private Set<Airport> neighborFrom = null;

    public Airport() {
        neighbor = new LinkedHashSet<Airport>();
        neighborFrom = new LinkedHashSet<Airport>();
    }

    public Airport(String id,int capacity, Set<Airport> neighbor, Set<Airport> neighborFrom) {
        this.id = id;
        this.capacity = capacity;
        if(neighbor != null){
            this.neighbor = neighbor;
        }else{
            this.neighbor = new LinkedHashSet<Airport>();
        }
        if(neighborFrom != null){
            this.neighborFrom = neighborFrom;
        }else {
            this.neighborFrom = new LinkedHashSet<Airport>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Airport> getNeighbour() {
        return neighbor;
    }

    public void setNeighbour(Set<Airport> neighbor) {
        this.neighbor = neighbor;
    }

    public Set<Airport> getNeighbourFrom() {
        return neighborFrom;
    }

    public void setNeighbourFrom(Set<Airport> neighborFrom) {
        this.neighborFrom = neighborFrom;
    }

    public void addNeighbor(Airport airport){
        if(!this.neighborFrom.contains(airport)){
            this.neighborFrom.add(airport);
            airport.addNeighbor(this);
        }
        if(!this.neighbor.contains(airport)){
            this.neighbor.add(airport);
            airport.addNeighbor(this);
        }
    }
}
