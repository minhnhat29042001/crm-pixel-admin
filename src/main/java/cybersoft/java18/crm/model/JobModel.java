package cybersoft.java18.crm.model;

import lombok.Data;
import java.util.Date;

@Data
public class JobModel {
    Integer id;
    String name;
    Date startDate;
    Date endDate;
}
