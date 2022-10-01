package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.services.RoleServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// api/role
@WebServlet(name = "role",urlPatterns = {
        UrlUtils.URL_ROLE,
        UrlUtils.URL_ROLE_ADD
})
public class RoleController extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("kiemtra do get");

//        RoleModel roleModel = new RoleModel();
//        roleModel.setId(1);
//        roleModel.setName("Nguyễn Văn Test");
//        roleModel.setDescription("Đây là mô tả về JSON");

        List<RoleModel> listRoles =  RoleServices.getInstance().getAllRole();

        String json = gson.toJson(listRoles);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("kiemtra do post");
    }


}
