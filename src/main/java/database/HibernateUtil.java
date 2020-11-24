package database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

 public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    private static HibernateUtil instance = null;

     private HibernateUtil() {
        sessionFactory = buildSessionFactory();
     }
 /*  singleton */
     public static HibernateUtil getInstance() {
         if (instance == null) {
             instance = new HibernateUtil();
         }
         return instance;
     }

    private static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
