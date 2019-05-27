package com.yx.simpaledata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yx start
 * @create 2019/5/26,15:56
 */
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String noPermiss(){
        return "index" ;
    }

    @GetMapping(value = "/login")
    public String yxShow(){
        return "login" ;
    }
}
