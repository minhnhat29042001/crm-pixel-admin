package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.TaskModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository extends AbstractRepository<JobModel> {
  public  List<JobModel> getAllJobs(){
        return excuteQuery(connection -> {
            List<JobModel> jobModelList = new ArrayList<>();
            String query="select * from jobs";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                JobModel jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                java.util.Date  startDate = new java.util.Date(resultSet.getDate("start_date").getTime());
                jobModel.setStartDate(startDate);
                java.util.Date  endDate = new java.util.Date(resultSet.getDate("end_date").getTime());
                jobModel.setEndDate(endDate);
                jobModelList.add(jobModel);
            }
            return jobModelList;
        });
    }

    public Integer deleteJob(Integer id){
        return excuteSaveAndUpdate((connection)->{
            String query = "delete from jobs where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate();
        });
    }

    public Integer updateJob(JobModel jobModel){
        return excuteSaveAndUpdate((connection) -> {
            String query = "update jobs set name=?,start_date=?,end_date=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, jobModel.getName());
            preparedStatement.setDate(2, (Date) jobModel.getStartDate());
            preparedStatement.setDate(3, (Date) jobModel.getEndDate());
            preparedStatement.setInt(4,  jobModel.getId());

            return  preparedStatement.executeUpdate();
        });
    }

    public Integer saveJob(String name, Date star_date, Date end_date){
        return excuteSaveAndUpdate((connection) -> {
            String query = "insert into jobs(name,start_date,end_date) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setDate(2, (Date) star_date);
            preparedStatement.setDate(3, (Date) end_date);
            return  preparedStatement.executeUpdate();
        });
    }



}
