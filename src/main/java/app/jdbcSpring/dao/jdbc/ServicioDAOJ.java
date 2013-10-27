package app.jdbcSpring.dao.jdbc;

import app.jdbcSpring.dao.ServicioDAO;
import app.jdbcSpring.model.Servicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/*
http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/jdbc.html
*/
public class ServicioDAOJ extends JdbcDaoSupport implements ServicioDAO {

    public List<Servicio> list() {
        String sql = "select * from Servicio";

        List<Servicio> servicios = new ArrayList<Servicio>();

        List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql);

        for (Map row : rows) {

            Servicio oServicio = new Servicio();
            oServicio.setId(Long.parseLong(String.valueOf(row.get("ID"))));
            oServicio.setDescripcion((String) row.get("descripcion"));
            servicios.add(oServicio);
        }
        return servicios;
    }
    
    public Servicio get(Servicio t) {
        String sql = "select * from Servicio where id = ?";

        Servicio serv = (Servicio) this.getJdbcTemplate().queryForObject(
                          sql, new Object[]{t.getId()}, new ServicioRowMapper());

        return serv;
    }

    public void save(Servicio t) {
        String sql = "insert into Servicio ( id, descripcion, costo_hora) "
                   + "values(?, ?, ?);";

        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                t.getId(),
                t.getDescripcion(),
                t.getCostoHora()
            });
        } catch (DataAccessException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public void update(Servicio t) {
        String sql = "update Servicio set descripcion=?, costo_hora=? "
                + " where id=?";

        try {
            this.getJdbcTemplate().update(sql, new Object[]{                
                t.getDescripcion(),
                t.getCostoHora(),                
                t.getId()
            });
        } catch (DataAccessException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public void delete(Servicio t) {
        this.getJdbcTemplate().update("delete from Servicio where id=?",
                new Object[]{t.getId()});
    }
 
    
}
