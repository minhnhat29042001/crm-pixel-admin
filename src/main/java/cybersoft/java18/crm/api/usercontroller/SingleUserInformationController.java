package cybersoft.java18.crm.api.usercontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.services.UserServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GuardedObject;

@WebServlet(name = "userinformation",urlPatterns = {
        UrlUtils.URL_USER_INFORMATION
})
public class SingleUserInformationController extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        UserModel userModel = UserServices.getInstance().getSingleUserById(id);
        String json = gson.toJson(userModel);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
