package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.repository.JobRepository;

import java.sql.Date;
import java.util.List;

public class JobServices {
    private static JobServices INSTANCE=null;
    private JobRepository jobsRepository = new JobRepository();

    public static JobServices getInstance(){
        if(INSTANCE==null){
            INSTANCE= new JobServices();
        }
        return INSTANCE;
    }

    public List<JobModel> getAllJobs(){ return jobsRepository.getAllJobs();}

    public Integer updateJob(JobModel jobModel){return jobsRepository.updateJob(jobModel);}

    public Integer deleteJob(Integer id){return jobsRepository.deleteJob(id);}

    public Integer saveJob (String name, Date star_date, Date end_date){return jobsRepository.saveJob(name,star_date,end_date); }
}
