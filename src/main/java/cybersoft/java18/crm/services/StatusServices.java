package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.StatusModel;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.repository.StatusRepository;
import cybersoft.java18.crm.repository.UserRepository;

import java.util.List;

public class StatusServices {
    private static StatusServices INSTANCE=null;
    private StatusRepository statusRepository= new StatusRepository();

    public static StatusServices getInstance(){
        if(INSTANCE==null){
            INSTANCE= new StatusServices();
        }
        return INSTANCE;
    }

    public List<StatusModel> getAllStatus(){ return statusRepository.getAllStatus();}

    public Integer updateStatus(StatusModel  statusModel){return statusRepository.updateStatus(statusModel);}

    public Integer deleteStatus(Integer id){return  statusRepository.deleteStatus(id);}

    public Integer saveStatus (String name){return statusRepository.saveStatus(name); }
}
