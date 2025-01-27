package es.ada.u3.hibernate.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "IDAIRPORT", nullable = false,length = 3)
    private String id;

    @Column(name = "AIRPORTCAPACITY")
    private int capacity;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.REMOVE)
    private Set<Airplane> airplanes = new LinkedHashSet<Airplane>();

    @ManyToMany
    @JoinTable(
            name = "neighbor",
            joinColumns = @JoinColumn(name = "IDAIRPORT_A"),
            inverseJoinColumns = @JoinColumn(name = "IDAIRPORT_B")
    )
    private Set<Airport> neighbors = null;

    @ManyToMany(mappedBy = "neighbors")
    private Set<Airport> neighborsFrom = null;


    public Airport() {
        neighbors = new LinkedHashSet<Airport>();
        neighborsFrom = new LinkedHashSet<Airport>();
    }

    public Airport(String id,int capacity, Set<Airport> neighbors, Set<Airport> neighborsFrom) {
        this.id = id;
        this.capacity = capacity;
        if(neighbors != null){
            this.neighbors = neighbors;
        }else{
            this.neighbors = new LinkedHashSet<Airport>();
        }
        if(neighborsFrom != null){
            this.neighborsFrom = neighborsFrom;
        }else {
            this.neighborsFrom = new LinkedHashSet<Airport>();
        }

    }

    public void addAirplane(Airplane airplane){
        airplanes.add(airplane);
    }
    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
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
        return neighbors;
    }

    public void setNeighbour(Set<Airport> neighbors) {
        this.neighbors = neighbors;
    }

    public Set<Airport> getNeighbourFrom() {
        return neighborsFrom;
    }

    public void setNeighbourFrom(Set<Airport> neighborsFrom) {
        this.neighborsFrom = neighborsFrom;
    }

    public void addNeighbor(Airport airport){
        if(!this.neighborsFrom.contains(airport)){
            this.neighborsFrom.add(airport);
            airport.addNeighbor(this);
        }
        if(!this.neighbors.contains(airport)){
            this.neighbors.add(airport);
            airport.addNeighbor(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Airport{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", airplanes id { ");

        for(Airplane airplane : airplanes){
            sb.append("id = " + airplane.getId() + " ");
        }
        sb.append("}");
        return sb.toString();
    }
}
