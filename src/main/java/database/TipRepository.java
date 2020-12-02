package database;

import entity.Lang;
import entity.Tip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TipRepository {

    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public Tip findByNickName(String nickName) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Tip result = session.get(Tip.class,nickName);
        transaction.commit();
        session.close();
        return result;
    }

    public List<Tip> findAllTips() {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Tip> result = session.createQuery("from Tip", Tip.class).list();
        transaction.commit();
        session.close();
        return result;
    }

}
