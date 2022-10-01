package cybersoft.java18.crm.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcExcute<T> {
    T processor(Connection connection) throws SQLException;
}
