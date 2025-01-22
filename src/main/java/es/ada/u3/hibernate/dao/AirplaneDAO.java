package es.ada.u3.hibernate.dao;

import es.ada.u3.hibernate.entities.Airplane;
import es.ada.u3.hibernate.dao.AirplaneTypeDAO;
import es.ada.u3.hibernate.utils.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirplaneDAO {
    private static AirplaneDAO instance = new AirplaneDAO();
    private AirplaneDAO(){

    }
    public static AirplaneDAO getInstance(){
        return instance;
    }

    public Airplane addAirplane(Airplane airplane){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        tx = session.beginTransaction();
        session.persist(airplane);
        tx.commit();
        return airplane;
    }
    public List<Airplane> loadAirplanes()throws HibernateException {
        AirplaneTypeDAO airplanetype = AirplaneTypeDAO.getInstance();
        airplanetype.loadAirplaneTypes();
        Session session = HibernateSessionFactory.getSessionSingleton();
        TypedQuery<Airplane> query = session.createNativeQuery("select * FROM Airplane", Airplane.class);
        return query.getResultList();
    }
}
