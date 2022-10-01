package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<UserModel> {
   public List<UserModel> getAllUser(){
      return   excuteQuery(connection -> {
            List<UserModel> userModelList = new ArrayList<>();
            String query="select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setPhonenumber(resultSet.getString("phonenumber"));
                userModel.setCountry(resultSet.getString("country"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                userModelList.add(userModel);
            }
            return userModelList;
        });
    }

    public Integer deleteUser (Integer id){
        return excuteSaveAndUpdate(connection -> {
            String query ="delete from users where id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        });
    }

    public Integer updateUser (UserModel user){
        return excuteSaveAndUpdate(connection -> {
            String query = "update users set email=?,password=?,fullname=?,avatar=?,phonenumber=?,country=?,role_id=? WHERE id=? ";
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFullName());
            preparedStatement.setString(4,user.getAvatar());
            preparedStatement.setString(5,user.getPhonenumber());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setInt(7,user.getRoleId());
            preparedStatement.setInt(8,user.getId());
            return  preparedStatement.executeUpdate();
        });
    }

    public Integer updateUserImage (Integer id,String filename){
        return excuteSaveAndUpdate(connection -> {
            String query = "update users set avatar=? WHERE id=? ";
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,filename);
            preparedStatement.setInt(2,id);
            return  preparedStatement.executeUpdate();
        });
    }

    public Integer saveUser (String email,String password,String fullname,String avatar,Integer roleId,String phonenumber,String country){
        return excuteSaveAndUpdate(connection -> {
           String query = "insert into users(email,password,fullname,avatar,phonenumber,country,role_id) values(?,?,?,?,?,?,?) ";
           PreparedStatement preparedStatement =connection.prepareStatement(query);
           preparedStatement.setString(1,email);
           preparedStatement.setString(2,password);
           preparedStatement.setString(3,fullname);
           preparedStatement.setString(4,avatar);
            preparedStatement.setString(5,phonenumber);
            preparedStatement.setString(6,country);
            preparedStatement.setInt(7,roleId);
           return preparedStatement.executeUpdate();
        });
    }

    public UserModel getSingleUserById(Integer id){
       return excuteSingleQuery(connection -> {
           String query="select * from users where id=?";
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1,id);
           ResultSet resultSet =   preparedStatement.executeQuery();
           UserModel userModel = new UserModel();
           if(resultSet.next()){
               userModel.setId(resultSet.getInt("id"));
               userModel.setFullName(resultSet.getString("fullname"));
               userModel.setEmail(resultSet.getString("email"));
               userModel.setPassword(resultSet.getString("password"));
               userModel.setAvatar(resultSet.getString("avatar"));
               userModel.setPhonenumber(resultSet.getString("phonenumber"));
               userModel.setCountry(resultSet.getString("country"));
               userModel.setRoleId(resultSet.getInt("role_id"));
           }
           return userModel;

       });
    }

}
