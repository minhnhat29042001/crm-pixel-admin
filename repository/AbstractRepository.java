package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.jdbc.MysqlConnection;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractRepository<T> {

//    Get All -> List Data
    public List<T> excuteQuery(JdbcExcute<List<T>> process){
        try{
            Connection connection = MysqlConnection.getConnection();
            //Lamda Function
            return process.processor(connection);
        }catch (Exception e){
            throw new RuntimeException("Error connect database");
//            System.out.println("Error connect database " + e.getMessage());
        }
    }

}
