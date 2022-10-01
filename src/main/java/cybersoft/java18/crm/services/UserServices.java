package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.repository.UserRepository;

import java.util.List;

public class UserServices {
    private static UserServices INSTANCE=null;
    private UserRepository userRepository = new UserRepository();

    public static UserServices getInstance(){
        if(INSTANCE==null){
            INSTANCE= new UserServices();
        }
        return INSTANCE;
    }

    public List<UserModel> getAllUser(){ return userRepository.getAllUser();}

    public Integer updateUser(UserModel  userModel){return userRepository.updateUser(userModel);}
    public Integer updateUserImage(Integer id,String filename){return userRepository.updateUserImage(id,filename);}

    public Integer deleteUser(Integer id){return  userRepository.deleteUser(id);}

    public Integer saveUser (String email, String password, String fullname, String avatar,Integer roleId,String phonenumber,String country){return  userRepository.saveUser(email, password, fullname, avatar,roleId,phonenumber,country);}

    public UserModel getSingleUserById(Integer id) {return  userRepository.getSingleUserById(id);}
}
