package cybersoft.java18.crm.model;

import lombok.Data;

import java.util.Date;

@Data
public class TaskModel {
    Integer id;
    String name;
    Date startDate;
    Date endDate;
    int userId;
    int jobId;
    int statusId;

}
