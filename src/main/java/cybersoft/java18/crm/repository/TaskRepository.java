package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.TaskModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository<TaskModel> {
    public List<TaskModel> getAllTask(){
        return excuteQuery(connection -> {
            List<TaskModel> taskModelList = new ArrayList<>();
            String query="select * from tasks";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setStatusId(resultSet.getInt("status_id"));
                taskModelList.add(taskModel);
            }
            return taskModelList;
        });
    }

    public Integer deleteTask(Integer id){
        return excuteSaveAndUpdate((connection)->{
            String query = "delete from tasks where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        });
    }

    public Integer updateTask(TaskModel taskModel){
        return excuteSaveAndUpdate((connection) -> {
            String query = "update tasks set name=?,start_date=?,end_date=?,user_id=?,job_id=?,end_date=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskModel.getName());
            preparedStatement.setDate(2, (Date) taskModel.getStartDate());
            preparedStatement.setDate(3, (Date) taskModel.getEndDate());
            preparedStatement.setInt(4,  taskModel.getUserId());
            preparedStatement.setInt(5,  taskModel.getJobId());
            preparedStatement.setInt(6,  taskModel.getStatusId());
            preparedStatement.setInt(7,  taskModel.getId());

            return  preparedStatement.executeUpdate();
        });
    }

    public Integer saveTask(String name, Date star_date, Date end_date,Integer userId,Integer jobId,Integer statusId){
        return excuteSaveAndUpdate((connection) -> {
            String query = "insert into tasks(name,start_date,end_date,userId,jobId,statusId) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setDate(2, (Date) star_date);
            preparedStatement.setDate(3, (Date) end_date);
            preparedStatement.setInt(4,userId);
            preparedStatement.setInt(5,jobId);
            preparedStatement.setInt(6, statusId);
            return  preparedStatement.executeUpdate();
        });
    }

    public List<TaskModel> getAllUserTaskByUserId(Integer userId){
        return excuteQuery(connection -> {
            List<TaskModel> taskModelList = new ArrayList<>();
            String query="select * from tasks where user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setStatusId(resultSet.getInt("status_id"));
                taskModelList.add(taskModel);
            }
            return taskModelList;
        });
    }

    public List<TaskModel> getAllTaskByJobId(Integer jobId){
        return excuteQuery(connection -> {
            List<TaskModel> taskModelList = new ArrayList<>();
            String query="select * from tasks where job_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setStatusId(resultSet.getInt("status_id"));
                taskModelList.add(taskModel);
            }
            return taskModelList;
        });
    }


}
