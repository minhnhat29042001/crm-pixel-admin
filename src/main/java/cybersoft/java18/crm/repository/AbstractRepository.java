package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.jdbc.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractRepository<T> {

//    Get All -> List Data
    protected List<T> excuteQuery(JdbcExcute<List<T>> process){
        try{
            Connection connection = MysqlConnection.getConnection();
            //Lamda Function
            return process.processor(connection);
        }catch (Exception e){
            throw new RuntimeException("Error connect database");
//            System.out.println("Error connect database " + e.getMessage());
        }
    }

    protected Integer excuteSaveAndUpdate(JdbcExcute<Integer> process){
        try{
            Connection connection = MysqlConnection.getConnection();
            //Lamda Function
            return process.processor(connection);
        }catch (Exception e){
            throw new RuntimeException("Error connect database");
//            System.out.println("Error connect database " + e.getMessage());
        }
    }

    public T excuteSingleQuery(JdbcExcute<T> process){
        try{
            Connection connection = MysqlConnection.getConnection();
            return process.processor(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error connect database");
        }
    }

}
