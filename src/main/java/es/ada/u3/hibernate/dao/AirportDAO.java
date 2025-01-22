package es.ada.u3.hibernate.dao;

import es.ada.u3.hibernate.entities.Airport;
import es.ada.u3.hibernate.entities.Airport;
import es.ada.u3.hibernate.utils.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirportDAO {
    private static AirportDAO instance = new AirportDAO();
    private AirportDAO(){
    }
    public static AirportDAO getInstance(){
        return instance;
    }

    public Airport addAirport(Airport airport)throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        tx = session.beginTransaction();
        session.persist(airport);
        tx.commit();
        return airport;
    }
        public List<Airport> loadAirports()throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        TypedQuery<Airport> query = session.createNativeQuery("select * FROM airplanetype", Airport.class);
        return query.getResultList();
    }
    public void updateAirportNeighborhood(Airport airportFrom, Airport airportTo)throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        tx = session.beginTransaction();
        Airport airportFrom1 = (Airport) session.get(Airport.class, airportFrom.getId());
        Airport airportTo1 = (Airport) session.get(Airport.class, airportTo.getId());
        airportFrom1.addNeighbor(airportTo1);
        session.merge(airportFrom1);
        session.merge(airportTo1);
        tx.commit();
    }
    public void updateAirportNeighborhood(String airportFromID, String airportToID)throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        tx = session.beginTransaction();
        Airport airportFrom1 = (Airport) session.get(Airport.class, airportFromID);
        Airport airportTo1 = (Airport) session.get(Airport.class, airportToID);
        airportFrom1.addNeighbor(airportTo1);
        session.merge(airportFrom1);
        session.merge(airportTo1);
        tx.commit();
    }

}
