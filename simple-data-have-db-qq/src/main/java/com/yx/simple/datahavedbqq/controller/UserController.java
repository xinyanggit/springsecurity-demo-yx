package com.yx.simple.datahavedbqq.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author yx start
 * @create 2019/5/26,15:56
 */
@Controller
public class UserController {

    @GetMapping(value = "/yx/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        return "user/user";
    }
}
