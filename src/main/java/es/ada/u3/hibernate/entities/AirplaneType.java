package es.ada.u3.hibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class AirplaneType {
    @Id

    private String airplaneTypeName;

}
