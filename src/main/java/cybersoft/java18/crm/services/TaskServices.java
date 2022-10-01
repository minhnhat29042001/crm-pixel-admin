package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.repository.JobRepository;
import cybersoft.java18.crm.repository.TaskRepository;

import java.sql.Date;
import java.util.List;

public class TaskServices {
    private static TaskServices INSTANCE=null;
    private TaskRepository taskRepository = new TaskRepository();

    public static TaskServices getInstance(){
        if(INSTANCE==null){
            INSTANCE= new TaskServices();
        }
        return INSTANCE;
    }

    public List<TaskModel> getAllTask(){ return taskRepository.getAllTask();}
    public List<TaskModel> getAllUserTaskByUserId(Integer userId){ return taskRepository.getAllUserTaskByUserId(userId);}
    public List<TaskModel> getAllTaskByJobId(Integer jobId){ return taskRepository.getAllTaskByJobId(jobId);}
    public Integer updateTask(TaskModel taskModel){return taskRepository.updateTask(taskModel);}

    public Integer deleteTask(Integer id){return taskRepository.deleteTask(id);}

    public Integer saveTask(String name, Date star_date, Date end_date,Integer userId,Integer jobId,Integer statusId){return taskRepository.saveTask(name, star_date, end_date,userId,jobId,statusId) ;}
}
