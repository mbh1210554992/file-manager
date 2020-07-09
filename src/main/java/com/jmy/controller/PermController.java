package com.jmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perm/")
public class PermController {

    @RequestMapping("listUI")
    public String list(){
        return "system/perm_list";
    }
}
