package com.oauth.oauthserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String unsecured(){
        return "Unsecured";
    }

    @GetMapping("/login")
    public String secured(){
        return "Secured";
    }
}
