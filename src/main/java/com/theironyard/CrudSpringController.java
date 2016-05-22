package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CrudSpringController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String webRoot(Model model, HttpSession session){

        return "home";

    }

}
