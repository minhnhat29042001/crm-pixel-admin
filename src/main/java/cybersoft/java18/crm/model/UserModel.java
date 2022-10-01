package cybersoft.java18.crm.model;

import lombok.Data;

@Data
public class UserModel {
    Integer id;
    String email;
    String password;
    String fullName;
    String avatar;
    Integer roleId;
    String phonenumber;
    String country;
}
