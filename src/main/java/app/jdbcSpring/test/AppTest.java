package app.jdbcSpring.test;

import app.jdbcSpring.dao.ServicioDAO;
import app.jdbcSpring.model.Servicio;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    public static void main(String[] args) {
         // AppTest.saveServicio();
        AppTest.getAll();
        // AppTest.getLocal(new Local(13));
    }

    public static void saveServicio() {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_database.xml");
        ServicioDAO localDAO = (ServicioDAO) context.getBean("servicioDAO");

        Servicio oServicio = new Servicio();
        oServicio.setId(4);
        oServicio.setDescripcion("Servicio Almuerzo");
        oServicio.setCostoHora(100.02);

        localDAO.save(oServicio);
    }

    public static void getServicio(Servicio service) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_database.xml");

        ServicioDAO servicioDAO = (ServicioDAO) context.getBean("ServicioDAO");
        Servicio servicioFull = servicioDAO.get(service);
        System.out.println(servicioFull.getDescripcion() + " " + servicioFull.getCostoHora());        
    }

    public static void getAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_database.xml");
        ServicioDAO servicioDAO = (ServicioDAO) context.getBean("servicioDAO");
        List<Servicio> servicios = servicioDAO.list();

        for (Servicio service : servicios) {
            System.out.println(service.getId()+ " " + service.getDescripcion());
        }
    }
       

}
