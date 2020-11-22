
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class LangRepository {



    public Lang findById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Lang result = session.get(Lang.class,id);
        transaction.commit();
        session.close();
        return result;
    }

    public List<Lang> findAll() {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<Lang> result = session.createQuery("from Lang", Lang.class).list();
            transaction.commit();
            session.close();
            return result;
    }
}
