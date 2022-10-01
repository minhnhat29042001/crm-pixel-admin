package cybersoft.java18.crm.api.jobcontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.services.TaskServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "jobtask",urlPatterns = {
        UrlUtils.URL_JOB_TASK
})
public class SingleJobTaskJController extends HttpServlet {
    private Gson gson = new Gson();
//    Trả về tất cả các task của job bằng job id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer jobId = Integer.valueOf(req.getParameter("jobid"));
        List<TaskModel> taskModelList = TaskServices.getInstance().getAllTaskByJobId(jobId);
        String json = gson.toJson(taskModelList);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
