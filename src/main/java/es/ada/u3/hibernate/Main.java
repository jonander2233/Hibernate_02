package es.ada.u3.hibernate;

import es.ada.u3.hibernate.dao.AirplaneDAO;
import es.ada.u3.hibernate.dao.AirplaneTypeDAO;
import es.ada.u3.hibernate.dao.AirportDAO;
import es.ada.u3.hibernate.entities.Airplane;
import es.ada.u3.hibernate.entities.AirplaneType;
import es.ada.u3.hibernate.entities.Airport;
import es.ada.u3.hibernate.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
        private static AirplaneTypeDAO typeDAO = AirplaneTypeDAO.getInstance();
        private static AirportDAO airportDAO = AirportDAO.getInstance();
        private static AirplaneDAO airplaneDAO = AirplaneDAO.getInstance();

    public static void main(String[] args) {
        consultarAvionesPertenecientes(200);
    }
//------------------------------------------hql_querys------------------------------------------------------------------------
    private static void consultarAvionesPertenecientes(int tamano){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Query<Airplane> queryAirplanes = session.createQuery("FROM Airplane a WHERE a.airport.capacity > :capacity ",Airplane.class);
        queryAirplanes.setParameter("capacity",tamano);
        List<Airplane> relatedAirplanes = queryAirplanes.list();
        for (Airplane airplane : relatedAirplanes){
            System.out.println(airplane.toString());
        }
    }


    private static void listarAvionesPorTamanyoDelTipoYporID(){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Query<Airplane> queryAirplanes = session.createQuery("FROM Airplane a ORDER BY a.airplaneType.airplaneTypeSize, a.id ",Airplane.class);
        List<Airplane> relatedAirplanes = queryAirplanes.list();
        for (Airplane airplane : relatedAirplanes){
            System.out.println(airplane.toString());
        }
    }

    private static void listarAvionesPorIdDescendiente(){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Query<Airplane> queryAirplanes = session.createQuery("FROM Airplane ORDER BY id DESC",Airplane.class);
        List<Airplane> relatedAirplanes = queryAirplanes.list();
        for (Airplane airplane : relatedAirplanes){
            System.out.println(airplane.toString());
        }

    }

    private static void consultarAvionesPorNombreDeTipo(){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Query<Airplane> queryAirplanes = session.createQuery("FROM Airplane a WHERE a.airplaneType.airplaneTypeName = :type",Airplane.class);
        queryAirplanes.setParameter("type","small");
        List<Airplane> relatedAirplanes = queryAirplanes.list();
        for (Airplane airplane : relatedAirplanes){
            System.out.println(airplane.toString());
        }

    }

    private static void hqlquerys(){
        Session session = HibernateSessionFactory.getSessionSingleton();

        Query<Airplane> queryAirplanes = session.createQuery("FROM Airplane a WHERE a.id = :id",Airplane.class);
        queryAirplanes.setParameter("id",111);
        List<Airplane> relatedAirplanes = queryAirplanes.list();
        for (Airplane airplane : relatedAirplanes){
            System.out.println(airplane.toString());
        }
    }


    //---------------------------------------anotaciones-----------------------------------------------------

    private static void  borrarAeropuertos(){
        airportDAO.deleteAirports();
    }
    private static void listarAeropuertos(){
        ArrayList<Airport> airports = (ArrayList<Airport>) airportDAO.loadAirports();
        for(Airport airport : airports){
            System.out.println(airport.toString());
        }
    }
    private static void listarAviones(){
        ArrayList<Airplane> airplanes =(ArrayList<Airplane>) airplaneDAO.loadAirplanes();
        for (Airplane airplane : airplanes){
            System.out.println(airplane.toString());
        }
    }
    private static void crear(){
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