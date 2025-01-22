package es.ada.u3.hibernate.dao;

import es.ada.u3.hibernate.entities.Airplane;
import es.ada.u3.hibernate.entities.AirplaneType;
import es.ada.u3.hibernate.entities.Airport;
import es.ada.u3.hibernate.utils.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirplaneTypeDAO {
    private static AirplaneTypeDAO instance = new AirplaneTypeDAO();
    private AirplaneTypeDAO(){

    }
    public static AirplaneTypeDAO getInstance(){
        return instance;
    }

    public AirplaneType addAirplaneType(AirplaneType airplaneType)throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        tx = session.beginTransaction();
        session.persist(airplaneType);
        tx.commit();
        return airplaneType;
    }
    public List<AirplaneType> loadAirplaneTypes()throws HibernateException {
        Session session = HibernateSessionFactory.getSessionSingleton();
        TypedQuery<AirplaneType> query = session.createNativeQuery("select * FROM airplanetype", AirplaneType.class);
        return query.getResultList();
    }

}
