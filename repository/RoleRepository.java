package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.jdbc.MysqlConnection;
import cybersoft.java18.crm.model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends AbstractRepository<RoleModel> {

    public List<RoleModel> getAllRole(){
        //Mo connection
        //PreparaStateMent
        //excuteQuery, excuteUpdate
            return excuteQuery((connection)->{
                List<RoleModel> roleModels = new ArrayList<>();

                String query = "select * from roles";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    RoleModel roleModel = new RoleModel();
                    roleModel.setId(resultSet.getInt("id"));
                    roleModel.setName(resultSet.getString("name"));
                    roleModel.setDescription(resultSet.getString("description"));

                    roleModels.add(roleModel);
                }

                return roleModels;
            });
    }

}
