package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.controller.ex.*;
import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.service.IUserService;
import com.wealth.shopmall.service.ex.InsertException;
import com.wealth.shopmall.service.ex.UserNameRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    /*限制文件的最大尺寸*/
    public static final long AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    public static final List<String> AVATER_TYPES = new ArrayList<>();

    static {
        AVATER_TYPES.add("image/jpeg");
        AVATER_TYPES.add("image/png");
        AVATER_TYPES.add("image/bmp");
        AVATER_TYPES.add("image/gif");
    }

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/register")
    //@ResponseBody 表示此方法的响应结果是以json格式进行响应返回给前端
    public HttpResult<Void> register(User user) {
        System.out.println(user.toString());
        iUserService.register(user);
        return new HttpResult<Void>(OK, "注册成功");
    }


    @RequestMapping("/login")
    public HttpResult<User> login(String username, String password, HttpSession session) {
        User user = iUserService.login(username, password);
        //登录成功后 将信息存在session中
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        //获取session中绑定的数据
        System.out.println("session中的uid==" + getUidFromSession(session));
        System.out.println("session中的username=" + getUsernameFromSession(session));
        return new HttpResult<User>(OK, "登录成功", user);
    }

    @RequestMapping("/change_password")
    public HttpResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        //从session中获取用户UID，Username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changePassword(uid, username, oldPassword, newPassword);
        return new HttpResult<>(OK, "修改成功");
    }

    @GetMapping("/get_user_info")
    public HttpResult<User> getUserById(HttpSession session) {
        Integer uid = getUidFromSession(session);
        User result = iUserService.getUserById(uid);
        return new HttpResult<User>(OK, "获取成功", result);
    }

    @RequestMapping("/change_user_info")
    public HttpResult<Void> changeUserInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changeInfo(uid, username, user);
        return new HttpResult<>(OK, "修改成功");
    }


    /**
     * MultipartFile接口是SpringMVC提供的一个接口，包装了获取文件的类型
     *
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("/change_avatar")
    public HttpResult<String> updateAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超出限制");
        }
        String fileContentType = file.getContentType();
        if (!AVATER_TYPES.contains(fileContentType)) {
            throw new FileTypeException("文件类型不支持");
        }

        //上传的文件.../uploda/文件.png
        String parent = session.getServletContext().getRealPath("upload");
        //File对象指向这个路径，file是否存在
        File dir = new File(parent);
        if (!dir.exists()) {//判断文件目录是否存在
            dir.mkdirs();//创建当前目录
        }
        //获取到文件名称，使用UUID工具生成随机的文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("文件名==" + originalFilename);

        //获取文件后缀 如.png
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir,filename);//dir目录下创建一个新的文件
        //讲file文件的数据写入dest中
        try {
            file.transferTo(dest);

        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
          throw new FileUploadIOException("文件读写异常");
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像路径
        String avatar = "/upload/"+filename;
        iUserService.updateAvatar(uid, avatar, username);
        return new HttpResult<String>(OK, "修改成功",avatar);
    }
}
