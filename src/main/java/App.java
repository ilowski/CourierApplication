
import database.HibernateUtil;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.*;


public class App {

    public static void main (String[] args)  throws Exception {
      Server server = new Server(8080);
      HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase("src/main/webapp");
        webapp.setContextPath("/");
        webapp.setConfigurations(new Configuration[]
                {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });

       webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");


      server.setHandler(webapp);
      server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
          @Override
          public void lifeCycleStopped(LifeCycle event) {
              hibernateUtil.close();
          }
      });
      server.start();
      server.join();



    }
}
