package com.tmall.web;

import com.tmall.pojo.Category;
import com.tmall.pojo.User;
import com.tmall.service.CategoryService;
import com.tmall.service.ProductService;
import com.tmall.service.UserService;
import com.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

import java.util.List;

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs= categoryService.list();
        //productService.fill(cs);
        //productService.fillByRow(cs);
        //categoryService.removeCategoryFromProduct(cs);
        return cs;
    }

    @PostMapping("/foreregister")
    public Object register(@RequestBody User user) {
        String name =  user.getName();
        String password = user.getPassword();
        int type = user.getUser_type();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if(exist){
            String message ="The user name is already in use and cannot be used";
            return Result.fail(message);
        }

        user.setPassword(password);
        user.setUser_type(type);

        userService.add(user);

        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name =  userParam.getName();
        name = HtmlUtils.htmlEscape(name);

        User user =userService.get(name,userParam.getPassword());
        if(null==user){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }
}