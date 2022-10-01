package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.StatusModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository extends AbstractRepository<StatusModel> {
    public List<StatusModel> getAllStatus(){
        return excuteQuery(connection -> {
            List<StatusModel> statusModelList = new ArrayList<>();
            String query="select * from status";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));

                statusModelList.add(statusModel);
            }
            return statusModelList;
        });
    }

    public Integer deleteStatus(Integer id){
        return excuteSaveAndUpdate((connection)->{
            String query = "delete from status where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        });
    }

    public Integer updateStatus(StatusModel statusModel){
        return excuteSaveAndUpdate((connection) -> {
            String query = "update jobs set name=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, statusModel.getName());

            return  preparedStatement.executeUpdate();
        });
    }

    public Integer saveStatus(String name){
        return excuteSaveAndUpdate((connection) -> {
            String query = "insert into status(name) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            return  preparedStatement.executeUpdate();
        });
    }
}
