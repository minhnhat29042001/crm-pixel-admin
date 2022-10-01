package cybersoft.java18.crm.api.usercontroller;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.services.UserServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "imageuser",urlPatterns = UrlUtils.URL_USER_IMG)
@MultipartConfig
public class ImageUserController extends HttpServlet {
    private Gson gson = new Gson();
//    Trả về hình của user bằng user id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String filename = UserServices.getInstance().getSingleUserById(id).getAvatar();
        resp.setContentType("image/jpeg");
        String pathToWeb = getServletContext().getRealPath("/images");
        File f = new File(pathToWeb+File.separator+filename);
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }


    // Update thông tin ảnh cho user bằng user id
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý lưu ảnh
        Integer id = Integer.valueOf(req.getParameter("id"));
        Part part = req.getPart("image");
        String realPath =req.getServletContext().getRealPath("/images");
        String filename= Path.of(part.getSubmittedFileName()).getFileName().toString();
        if(!Files.exists(Path.of(realPath))){
            Files.createDirectory(Path.of(realPath));
        } else{
            part.write(realPath+"/"+filename);
        }

        Integer result = UserServices.getInstance().updateUserImage(id,filename);

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
