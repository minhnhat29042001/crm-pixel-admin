package cybersoft.java18.crm.api.usercontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.services.TaskServices;
import cybersoft.java18.crm.services.UserServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "usertask", urlPatterns = {
        UrlUtils.URL_USER_TASK
})
public class SingleUserTaskController extends HttpServlet {
    private Gson gson =  new Gson();

//    Trả về các task của user bằng user id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("id"));
        List<TaskModel> taskModelUserList =  taskModelUserList = TaskServices.getInstance().getAllUserTaskByUserId(userId);
        String json = gson.toJson(taskModelUserList);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
