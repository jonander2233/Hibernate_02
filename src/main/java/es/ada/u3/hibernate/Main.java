package es.ada.u3.hibernate;

import es.ada.u3.hibernate.dao.AirplaneDAO;
import es.ada.u3.hibernate.dao.AirplaneTypeDAO;
import es.ada.u3.hibernate.dao.AirportDAO;
import es.ada.u3.hibernate.entities.Airplane;
import es.ada.u3.hibernate.entities.AirplaneType;
import es.ada.u3.hibernate.entities.Airport;

public class Main {
    public static void main(String[] args) {
        AirplaneTypeDAO typeDAO = AirplaneTypeDAO.getInstance();
        AirportDAO airportDAO = AirportDAO.getInstance();
        AirplaneDAO airplaneDAO = AirplaneDAO.getInstance();
        //crear los objetos en memoria
        AirplaneType airplaneType1 = new AirplaneType("small",10);
        AirplaneType airplaneType2 = new AirplaneType("medium",15);
        AirplaneType airplaneType3 = new AirplaneType("big",20);
        Airport airport1 = new Airport("01",200,null,null);
        Airport airport2 = new Airport("02",200,null,null);
        Airport airport3 = new Airport("03",200,null,null);
        Airplane airplane1 = new Airplane(111,airport1,airplaneType1);
        Airplane airplane2 = new Airplane(222,airport2,airplaneType2);
        Airplane airplane3 = new Airplane(333,airport3,airplaneType3);
//        airport1.addNeighbor(airport2);
//        airport3.addNeighbor(airport1);
//        airport3.addNeighbor(airport2);

        //guardar los objetos en bbdd
        typeDAO.addAirplaneType(airplaneType1);
        typeDAO.addAirplaneType(airplaneType2);
        typeDAO.addAirplaneType(airplaneType3);

        airportDAO.addAirport(airport1);
        airportDAO.addAirport(airport2);
        airportDAO.addAirport(airport3);

        airplaneDAO.addAirplane(airplane1);
        airplaneDAO.addAirplane(airplane2);
        airplaneDAO.addAirplane(airplane3);

        airportDAO.updateAirportNeighborhood(airport1,airport2);
        airportDAO.updateAirportNeighborhood(airport3,airport1);
        airportDAO.updateAirportNeighborhood(airport3,airport2);










    }
}