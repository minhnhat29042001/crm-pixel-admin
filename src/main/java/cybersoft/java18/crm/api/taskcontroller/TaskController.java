package cybersoft.java18.crm.api.taskcontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.services.JobServices;
import cybersoft.java18.crm.services.TaskServices;
import cybersoft.java18.crm.utils.UrlUtils;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "task",urlPatterns = {
        UrlUtils.URL_TASK
})
public class TaskController extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskModel> taskModelList = TaskServices.getInstance().getAllTask();
        String json = gson.toJson(taskModelList);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        String name = req.getParameter("name");
        Date startDate = (Date) dt.parse(req.getParameter("start_date"));
        Date endDate = (Date) dt.parse(req.getParameter("end_date"));
        Integer userId = Integer.valueOf(req.getParameter("user_id"));
        Integer jobId = Integer.valueOf(req.getParameter("job_id"));
        Integer statusId = Integer.valueOf(req.getParameter("status_id"));

        Integer result = TaskServices.getInstance().saveTask(name,startDate,endDate,userId,jobId,statusId);
        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Th??m th??nh c??ng
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Th??m th??nh c??ng !");
        }else{
            //Th??m th???t b???i
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Th??m  th???t b???i !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Integer result =TaskServices.getInstance().deleteTask(id);

        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Xo?? th??nh c??ng
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Xo?? th??nh c??ng !");
        }else{
            //Xo?? th???t b???i
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Xo?? th???t b???i !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(req.getReader());
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            builder.append(line);
        }
        String data = builder.toString();



        TaskModel taskModel = gson.fromJson(data,TaskModel.class);

        Integer result = TaskServices.getInstance().updateTask(taskModel);

        ResponseData responseData = new ResponseData();

        if(result == 1){
            //C???p nh???t th??nh c??ng
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("C???p nh???t th??nh c??ng !");
        }else{
            //C???p nh???t th???t b???i
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("C???p nh???t th???t b???i !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();


    }
}
