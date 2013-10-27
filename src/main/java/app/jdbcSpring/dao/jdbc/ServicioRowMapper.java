package app.jdbcSpring.dao.jdbc;

import app.jdbcSpring.model.Servicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ServicioRowMapper implements RowMapper<Servicio> {

    @Override
    public Servicio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Servicio local = new Servicio();
        
        local.setId(rs.getLong(1));
        local.setDescripcion(rs.getString(2));
        local.setCostoHora(rs.getDouble(3));

        return local;
    }

}
