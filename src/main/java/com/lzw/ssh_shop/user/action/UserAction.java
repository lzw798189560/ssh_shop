package com.lzw.ssh_shop.user.action;

import com.lzw.ssh_shop.user.service.UserService;
import com.lzw.ssh_shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户模块Action的类
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
    //模型驱动使用的对象
    private User user=new User();

    //注入UserService
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getModel(){
        return user;
    }

    //跳转到注册页面的执行方法
    public String registPage(){
        return "registPage";
    }

    //AJAX进行异步校验用户名的执行方法
    public String findByName() throws IOException {
        //调用Service进行查询
        User existUser=userService.findByUsername(user.getUsername());
        //获得response对象，项页面输出：
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        //判断
        if(existUser!=null){
            //查询到该用户：用户名已经存在
            response.getWriter().println("<font color='red'>用户名已经存在</font>");
        }else {
            //没有查询到该用户：用户名可以使用
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }
        return NONE;

    }

    //用户注册的方法：
    public String regist(){
        userService.save(user);
        this.addActionMessage("注册成功！请去邮箱激活！");
        return "msg";
    }

    //用户激活的方法
    public String active(){
        //根据激活码查询用户：
        User existUser=userService.findByCode(user.getCode());
        //判断
        if(existUser==null){
            //激活码错误的
            this.addActionMessage("激活失败：激活码错误！");
        }else {
            //激活成功
            //修改用户的状态
            existUser.setState(1);
            existUser.setCode(null);
            userService.update(existUser);
            this.addActionMessage("激活成功：请去登录！");
        }
        return "msg";
    }

    //跳转到登录页面
    public String loginPage(){
        return "loginPage";
    }
}
