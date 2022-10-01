package cybersoft.java18.crm.api.usercontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.services.UserServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@WebServlet(name = "user",urlPatterns = {
        UrlUtils.URL_USER
})
@MultipartConfig
public class UserController extends HttpServlet {
    private  Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> userModelList = UserServices.getInstance().getAllUser();
        String json = gson.toJson(userModelList);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phonenumber = req.getParameter("phonenumber");
            String country = req.getParameter("country");
            Integer roleId = Integer.valueOf(req.getParameter("role_id"));

            // Xử lý lưu ảnh
            Part part = req.getPart("image");
            String realPath =req.getServletContext().getRealPath("/images");
             String filename= Path.of(part.getSubmittedFileName()).getFileName().toString();
            if(!Files.exists(Path.of(realPath))){
                Files.createDirectory(Path.of(realPath));
            } else{
                part.write(realPath+"/"+filename);
            }




            Integer result = UserServices.getInstance().saveUser(email,password,fullname,filename,roleId,phonenumber,country);
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

           
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Integer result =UserServices.getInstance().deleteUser(id);

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





        UserModel userModel = gson.fromJson(data,UserModel.class);
        System.out.println(userModel);


        Integer result = UserServices.getInstance().updateUser(userModel);

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


    }
}
