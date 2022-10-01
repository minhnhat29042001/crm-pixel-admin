package cybersoft.java18.crm.api.rolecontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.services.RoleServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// api/role
@WebServlet(name = "role",urlPatterns = {
        UrlUtils.URL_ROLE,
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
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String description = req.getParameter("description");
        Integer result = RoleServices.getInstance().saveRole(role,description);
        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Thêm thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Thêm thành công !");
        }else{
            //Thêm thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Thêm  thất bại !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();
        System.out.println("kiemtra do post" + role + "-" + description);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer result = RoleServices.getInstance().deleteRoleById(id);
        System.out.println("kiemtra do detele " + result);

        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Xoá thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Xoá thành công !");
        }else{
            //Xoá thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Xoá thất bại !");
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

        RoleModel roleModel = gson.fromJson(data,RoleModel.class);

        Integer result = RoleServices.getInstance().updateRoleById(roleModel);

        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Cập nhật thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Cập nhật thành công !");
        }else{
            //Cập nhật thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Cập nhật thất bại !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();

        System.out.println("Kiemtra do Put " + roleModel.getId() + " - " + roleModel.getName());
    }


}
