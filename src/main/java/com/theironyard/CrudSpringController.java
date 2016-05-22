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
    public String webroot(HttpSession session){

        //redirects to create-game if user is logged in, otherwise redirects to webroot
        if (session.getAttribute("userName") != null){
            //return "redirect:/create-game";
            return "home";

        } else {
            return "home";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception{

        User user = userRepo.findFirstByName(userName);

        if (user == null){
            user = new User();
            user.setName(userName);
            user.setPassword(password);
            userRepo.save(user);

        } else if (!user.getPassword().equals(password)) {
            throw new Exception("Wrong password!");
        }

        session.setAttribute("userName", userName);
        return "redirect:/";

    }

}
